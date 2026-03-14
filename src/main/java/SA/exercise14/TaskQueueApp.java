package SA.exercise14;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskQueueApp {

    public void run(int developers, int executors, int tasksPerDeveloper, int queueSize, boolean autoConfig)
            throws Exception {

        if (autoConfig) {
            int cores = Runtime.getRuntime().availableProcessors();
            developers = cores;
            executors = cores * 2;
            System.out.println("Авто-конфигурация: " + cores + " ядер CPU");
        }

        System.out.println("=== Task Queue System ===");
        System.out.printf("Разработчики: %d | Исполнители: %d | Задач/разработчик: %d | Очередь: %d\n\n",
                developers, executors, tasksPerDeveloper, queueSize);

        TaskQueue queue = new TaskQueue(queueSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < developers; i++) {
            Thread t = new Thread(new Developer(queue, i, tasksPerDeveloper), "Developer-" + i);
            threads.add(t);
            executorService.submit(t);
        }

        for (int i = 0; i < executors; i++) {
            Thread t = new Thread(new Executor(queue, i), "Executor-" + i);
            threads.add(t);
            executorService.submit(t);
        }

        for (int i = 0; i < developers; i++) {
            threads.get(i).join();
        }
        System.out.println("\n📭 Все разработчики завершили создание задач");

        Thread.sleep(1000);

        queue.shutdown();

        for (int i = developers; i < threads.size(); i++) {
            threads.get(i).join();
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("\nСтатистика:");
        System.out.println("Создано задач: " + (developers * tasksPerDeveloper));
        System.out.println("Остаток в очереди: " + queue.size());
        System.out.println("Все потоки завершены!");
    }
}
