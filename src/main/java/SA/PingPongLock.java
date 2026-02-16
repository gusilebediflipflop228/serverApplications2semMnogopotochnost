package SA;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PingPongLock {
    private ReentrantLock lock = new ReentrantLock();
    private Condition pingCondition = lock.newCondition();
    private Condition pongCondition = lock.newCondition();
    private boolean isPingTurn = true;

    public void ping() {
        lock.lock();
        try {
            while (!isPingTurn) pingCondition.await();
            System.out.println("Ping");
            isPingTurn = false;
            pongCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void pong() {
        lock.lock();
        try {
            while (isPingTurn) pongCondition.await();
            System.out.println("Pong");
            isPingTurn = true;
            pingCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
