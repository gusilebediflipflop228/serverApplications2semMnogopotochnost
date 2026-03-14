package SA.exercise14;

import java.util.Random;

public class Developer implements Runnable {
    private final TaskQueue queue;
    private final int developerId;
    private final int tasksToCreate;
    private final Random random = new Random();

    public Developer(TaskQueue queue, int developerId, int tasksToCreate) {
        this.queue = queue;
        this.developerId = developerId;
        this.tasksToCreate = tasksToCreate;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < tasksToCreate; i++) {
                Task task = createRandomTask();
                queue.put(task);
                System.out.printf("Developer-%d создал: %s\n", developerId, task);
                Thread.sleep(random.nextInt(100) + 20);
            }
            System.out.printf("Developer-%d завершил (%d задач)\n", developerId, tasksToCreate);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("Developer-%d прерван\n", developerId);
        }
    }

    private Task createRandomTask() {
        if (random.nextBoolean()) {
            return new PrintTask("Задача от Dev-" + developerId);
        } else {
            return new MathTask(random.nextInt(100), random.nextInt(100));
        }
    }
}
