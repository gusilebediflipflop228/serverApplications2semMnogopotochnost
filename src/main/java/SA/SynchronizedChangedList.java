package SA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.random;

public class SynchronizedChangedList {
    private final List<Integer> list;

    public SynchronizedChangedList() {
        this.list = Collections.synchronizedList(new ArrayList<>());
    }

    public void add() {
        int randomNum = (int)(random()*1000);
        list.add(randomNum);
    }

    public void remove() {
        //synchronized (list){
        if (!list.isEmpty()) {
            int randomIndex = (int) (random() * list.size());
            list.remove(randomIndex);
        }
        //}
    }

    public int getSize() {
        return list.size();
    }
}
