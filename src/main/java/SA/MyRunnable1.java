package SA;

public class MyRunnable1 implements Runnable {
    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("Поток " + Thread.currentThread().getName() + " выполняет итерацию " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
