package SA.exercise14;

import java.util.Random;

public class Executor implements Runnable {
    private final TaskQueue queue;
    private final int executorId;
    private final Random random = new Random();

    public Executor(TaskQueue queue, int executorId) {
        this.queue = queue;
        this.executorId = executorId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Task task = queue.take();
                if (task == null) {
                    break;
                }
                System.out.printf("Executor-%d выполняет: %s\n", executorId, task);
                task.execute();
                Thread.sleep(random.nextInt(50) + 10);
            }
            System.out.printf("Executor-%d завершил\n", executorId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("Executor-%d прерван\n", executorId);
        }
    }
}
