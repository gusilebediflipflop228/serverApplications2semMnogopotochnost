package SA.exercise14;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Task implements Executable {
    private static final AtomicLong id = new AtomicLong(0);

    private final long taskId;
    private final long createdAt;

    public Task() {
        this.taskId = id.incrementAndGet();
        this.createdAt = System.currentTimeMillis();
    }

    public long getTaskId() {
        return taskId;
    }
    public long getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return String.format("%s[ID=%d]", getClass().getSimpleName(), taskId);
    }
}
