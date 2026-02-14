package SA;

import java.util.List;

public class RemovingThread implements Runnable{
    List<Integer> list;

    public RemovingThread(List<Integer> list){
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (list) {
                if (!list.isEmpty()) {
                    int randomIndex = (int) (Math.random() * list.size());
                    list.remove(randomIndex);
                }
            }
        }
            System.out.println("Поток " + Thread.currentThread().getName() + " завершил работу");
    }
}
