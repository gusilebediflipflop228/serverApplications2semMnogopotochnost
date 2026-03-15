package SA;

public class PingPong implements Runnable {
    private final boolean isPing;
    private final Object lock;
    private static boolean isPingTurn = true;

    public PingPong(boolean isPing, Object lock) {
        this.isPing = isPing;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    while (isPing != isPingTurn) {
                        lock.wait();
                    }

                    if (isPing) {
                        System.out.println("ping");
                    } else {
                        System.out.println("pong");
                    }
                    isPingTurn = !isPingTurn;
                    lock.notify();

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}


/*public class PingPong implements Runnable {
    private boolean isPing;
    private static volatile boolean isPingTurn;

    public PingPong(boolean isPing) {
        this.isPing = isPing;
    }
    @Override
    public void run() {
        while (true) {
            if (isPing && isPingTurn) {
                System.out.println("ping");
                isPingTurn = false;
            } else if (!isPing && !isPingTurn) {
                System.out.println("pong");
                isPingTurn = true;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}*/
