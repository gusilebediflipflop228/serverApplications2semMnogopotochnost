package SA.exercise13;

public class Reader implements Runnable {
    private final DataQueue queue;
    private final int readerId;

    public Reader(DataQueue queue, int readerId) {
        this.queue = queue;
        this.readerId = readerId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Data data = queue.take();
                if (data == null) {
                    break;
                }
                System.out.printf("Reader-%d прочитал: %s\n", readerId, data);
                Thread.sleep(500);
            }
            System.out.printf("Reader-%d завершил\n", readerId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("Reader-%d прерван\n", readerId);
        }
    }
}
