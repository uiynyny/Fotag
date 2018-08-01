package cs.fotag.model;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
    private int filter;
    public ArrayList<Integer> image=new ArrayList<>();


    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
        updateView();
    }

    public Model(){
        filter = 0;
    }

    public void updateView(){
        setChanged();
        notifyObservers();

    }
}
