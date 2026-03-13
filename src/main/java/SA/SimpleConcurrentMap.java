package SA;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class SimpleConcurrentMap {
    public static class RwLockMap<K, V> {
        private final Map<K, V> map = new HashMap<>();
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        public V get(K key) {
            lock.readLock().lock();
            try {
                return map.get(key);
            } finally {
                lock.readLock().unlock();
            }
        }

        public V put(K key, V value) {
            lock.writeLock().lock();
            try {
                return map.put(key, value);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    private static final int THREADS = 10;
    private static final int OPS_PER_THREAD = 100_000; // операции на поток
    private static final int READ_PERCENT = 80;
    private static final int KEY_RANGE = 1000; // для коллизий

    public static void runBenchmark() throws InterruptedException {
        System.out.println("=== Тест: ConcurrentHashMap vs ReadWriteLock ===\n");
        System.out.printf("Потоки: %d | Операций/поток: %d | Чтения: %d%%\n\n",
                THREADS, OPS_PER_THREAD, READ_PERCENT);

        testConcurrentHashMap();
        testRwLockMap();
    }

    private static void testConcurrentHashMap() throws InterruptedException {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        runTest("ConcurrentHashMap", map);
    }

    private static void testRwLockMap() throws InterruptedException {
        RwLockMap<Integer, Integer> map = new RwLockMap<>();
        runTest("ReadWriteLock", map);
    }

    private static void runTest(String name, Object map) throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch finish = new CountDownLatch(THREADS);

        for (int i = 0; i < THREADS; i++) {
            new Thread(() -> {
                try {
                    start.await();

                    for (int j = 0; j < OPS_PER_THREAD; j++) {
                        int key = (int) (Math.random() * KEY_RANGE);

                        if (Math.random() * 100 < READ_PERCENT) {
                            if (map instanceof ConcurrentHashMap) {
                                ((ConcurrentHashMap<Integer, Integer>) map).get(key);
                            } else {
                                ((RwLockMap<Integer, Integer>) map).get(key);
                            }
                        } else {
                            if (map instanceof ConcurrentHashMap) {
                                ((ConcurrentHashMap<Integer, Integer>) map).put(key, j);
                            } else {
                                ((RwLockMap<Integer, Integer>) map).put(key, j);
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish.countDown();
                }
            }).start();
        }

        long startTime = System.nanoTime();
        start.countDown();
        finish.await();
        long endTime = System.nanoTime();

        long ms = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        long totalOps = (long) THREADS * OPS_PER_THREAD;
        long speed = (ms > 0) ? (totalOps * 1000 / ms) : 0;

        System.out.printf("%-20s | %4d мс | %10d оп/сек\n", name, ms, speed);
    }
}