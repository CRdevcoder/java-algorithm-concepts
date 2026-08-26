package sortingalgorithms.classes;

import java.util.ArrayList;

import observerpattern.Observer;
import observerpattern.SortingEvent;
import observerpattern.Subject;

public abstract class SortAlgorithm<T extends Comparable<T>> implements Sorter<T> {

    // throw interrupt if interrupted.
    public void checkForInterrupts() throws InterruptedException{
        if(Thread.currentThread().isInterrupted()){
            throw new InterruptedException();
        }
    }

    // sorting method.
    @Override
    public ArrayList<T> sortList(ArrayList<T> list) throws InterruptedException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sortList'");
    }
    

    // Observer methods.
    @Override
    public void addObserver(Observer<SortingEvent> observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addObserver'");
    }

    @Override
    public void removeObserver(Observer<SortingEvent> observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeObserver'");
    }

    @Override
    public void notifyObservers(Subject<SortingEvent> subject, SortingEvent event) throws InterruptedException{
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyObservers'");
    }

    
}
