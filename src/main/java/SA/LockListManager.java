package SA;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Math.random;

public class LockListManager {
    private List<Integer> list;
    private ReentrantLock lock = new ReentrantLock();

    public LockListManager() {
        this.list = new ArrayList<>();
    }

    public void addRandomNum(){
        lock.lock();
        try {
            int randomNum = (int) (random() * 1000);
            list.add(randomNum);
        } finally {
            lock.unlock();
        }
    }

    public void removeRandomNum(){
        lock.lock();
        try {
            if (!list.isEmpty()) {
                int randomNum = (int) (random() * list.size());
                list.remove(randomNum);
            }
        } finally {
            lock.unlock();
        }
    }

    public int getSize(){
        return list.size();
    }

}
