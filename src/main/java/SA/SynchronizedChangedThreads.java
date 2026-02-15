package SA;

public class SynchronizedChangedThreads implements Runnable {
    private SynchronizedChangedList list;
    boolean isAdding;

    public SynchronizedChangedThreads(SynchronizedChangedList list, boolean isAdding) {
        this.list = list;
        this.isAdding = isAdding;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            if (isAdding){
                list.add();
            }
            else{
                list.remove();
            }
        }
    }
}
