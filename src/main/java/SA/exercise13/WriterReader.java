package SA.exercise13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WriterReader {
    public static void run(int writers, int readers, int itemsPerWriter, int queueSize) throws Exception {
        System.out.printf("Писатели: %d | Читатели: %d | Элементов/писатель: %d | Размер очереди: %d\n\n",
                writers, readers, itemsPerWriter, queueSize);

        DataQueue queue = new DataQueue(queueSize);
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < writers; i++) {
            Thread t = new Thread(new Writer(queue, i, itemsPerWriter), "Writer-" + i);
            threads.add(t);
            executor.submit(t);
        }

        for (int i = 0; i < readers; i++) {
            Thread t = new Thread(new Reader(queue, i), "Reader-" + i);
            threads.add(t);
            executor.submit(t);
        }

        for (int i = 0; i < writers; i++) {
            threads.get(i).join();
        }
        System.out.println("\n Все писатели завершили работу");

        Thread.sleep(500);
        queue.shutdown();

        for (int i = writers; i < threads.size(); i++) {
            threads.get(i).join();
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("\n Все потоки завершены!");
    }
}
