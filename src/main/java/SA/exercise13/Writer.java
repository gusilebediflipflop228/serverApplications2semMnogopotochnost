package SA.exercise13;

public class Writer implements Runnable {
    private final DataQueue queue;
    private final int writerId;
    private final int itemsToProduce;

    public Writer(DataQueue queue, int writerId, int itemsToProduce) {
        this.queue = queue;
        this.writerId = writerId;
        this.itemsToProduce = itemsToProduce;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < itemsToProduce; i++) {
                Data data = new Data(5);
                queue.put(data);
                System.out.printf("Writer-%d создал: %s\n", writerId, data);
                Thread.sleep(500);
            }
            System.out.printf("Writer-%d завершил (%d элементов)\n", writerId, itemsToProduce);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("Writer-%d прерван\n", writerId);
        }
    }
}
