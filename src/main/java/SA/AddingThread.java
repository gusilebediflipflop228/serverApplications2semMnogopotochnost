package SA;

import java.util.List;

import static java.lang.Math.random;

public class AddingThread implements Runnable{
    private List<Integer> list;

    public AddingThread(List<Integer> list){
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++){
            int randomNum = (int)(random()*1000);
            synchronized (list) {
                list.add(randomNum);
            }
        }
        System.out.println("Поток " + Thread.currentThread().getName() + " завершил работу");
    }

}
