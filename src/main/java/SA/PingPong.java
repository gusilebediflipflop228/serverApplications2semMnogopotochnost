package SA;

public class PingPong implements Runnable {
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
}
