package SA;

public class ChangedThreads implements Runnable {
    private ChangedList list;
    boolean isAdding;

    public ChangedThreads(ChangedList list, boolean isAdding) {
        this.list = list;
        this.isAdding = isAdding;
    }

    @Override
    public void run() {
    for (int i = 0; i < 10000; i++) {
    if (isAdding){
        list.addRandomNum();
    }
    else{
        list.removeByRandomIndex();
    }
        }
    }
}
