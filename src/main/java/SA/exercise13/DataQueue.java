package SA.exercise13;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

// обёртка над блокирующей очередью
public class DataQueue {
    private final BlockingQueue<Data> queue;
    private volatile boolean shutdown = false;

    public DataQueue(int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    public void put(Data data) throws InterruptedException {
        queue.put(data);
    }

    public Data take() throws InterruptedException {
        Data data = queue.poll(100, TimeUnit.MILLISECONDS);
        if (data == null && shutdown) {
            return null;
        }
        return data;
    }

    public void shutdown() {
        shutdown = true;
    }

    public int size() {
        return queue.size();
    }
}
