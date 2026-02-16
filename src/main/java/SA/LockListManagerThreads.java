package SA;

public class LockListManagerThreads implements Runnable {
    private LockListManager lm;
    private boolean isAdding;

    public  LockListManagerThreads(LockListManager lm,  boolean isAdding) {
        this.lm = lm;
        this.isAdding = isAdding;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 10000; i++){
            if (isAdding){
                lm.addRandomNum();
            }
            else{
                lm.removeRandomNum();
            }
        }
    }
}
