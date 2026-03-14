package SA.exercise14;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TaskQueue {
    private final BlockingQueue<Task> queue;
    private volatile boolean running = true;

    public TaskQueue(int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    public void put(Task task) throws InterruptedException {
        queue.put(task);
    }
    //* положить / взять
    public Task take() throws InterruptedException {
        Task task = queue.poll(100, TimeUnit.MILLISECONDS);
        if (task == null && running) {
            return null;
        }
        return task;
    }

    public void shutdown() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
    public int size() {
        return queue.size();
    }
}
