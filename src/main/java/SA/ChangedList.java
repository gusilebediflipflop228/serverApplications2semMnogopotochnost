package SA;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

public class ChangedList {
    private List<Integer> list;

    public ChangedList(){
        this.list = new ArrayList<>();
    }

    public synchronized void addRandomNum(){
        int randomNum = (int)(random()*1000);
        list.add(randomNum);
    }

    public synchronized void removeByRandomIndex(){
        if (!list.isEmpty()) {
            int randomIndex = (int)(random() * list.size());
            list.remove(randomIndex);
        }
    }

    public int getSize(){
        return list.size();
    }
}
