package sortingalgorithms.classes;

import java.util.ArrayList;
import java.util.List;

import observerpattern.Observer;
import observerpattern.SortingEvent;
import observerpattern.Subject;
import observerpattern.SortingEvent.ActionType;
import observerpattern.SortingEvent.DurationType;

import java.lang.Character;

// implements Bubble Sort

// subject.
public class BubbleSort<T extends Comparable<T>>  extends SortAlgorithm<T> implements Sorter<T> {

    // using Generics.

    // Sorts a given arraylist and returns it.
    // Returns ArrayList of type arguement T.
    // Elements must implement Comparable Interface for their own class.

    // stores observers (sorters) of this subject.
    private ArrayList<Observer<SortingEvent>> sorterObservers;
    // constructor.
    public BubbleSort() {
        this.sorterObservers = new ArrayList<>();
    }
    
    @Override
    public ArrayList<T> sortList(ArrayList<T> listArg) {

        int length = listArg.size();
        boolean swapped = false; // false if passes through array without swapping.

        // i is current iteration, j is current index.
        // outter loop (i)
        for (int i = 0; i < length - 1; i++) {

            swapped = false;
            // inner loop(j)
            for(int j=0; j < length - i - 1; j++)
            {   
                // checks if interrupted, throws interrupt exception if true
                //try {
                 //   checkForInterrupts();    
                //} catch (Exception e) {
                //    // TODO: handle exception
                //    return listArg;
                //}

                // notify comparison taking place.
                try {
                    notifyObservers(this, new SortingEvent(DurationType.FULL_STEP,ActionType.SCANNING,j,(j+1)));
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                    return listArg;
                }
                
                
                // returns num greater than 0 if j bigger than j+1
                if(listArg.get(j).compareTo( listArg.get(j+1)) > 0)
                {
                    swap(j,listArg);
                    //System.out.println( i + " - " + j + ": " + listArg + " - Swapped (" + j + ") " + listArg.get(j+1) + " with (" + (j+1) + ") " + listArg.get(j) + "\n");
                    swapped = true;

                    try {
                        notifyObservers(this, new SortingEvent(j,(j+1)));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        return listArg;
                    }
                    
                }
            }
            if(!swapped)
            {
                break;
            }
        }

        return listArg;
    }

    // swaps one element with it's rightwards neighbor, given it's arrayList and index.
    private void swap(int index,ArrayList<T> list)
    {
        // store element in temp
        T temp = list.get(index);
        // swapping elements
        list.set(index,list.get(index + 1));
        list.set(index + 1, temp);
        
    }


    // Subject interface methods for Observer pattern.
    //Add.
    @Override
    public void addObserver(Observer<SortingEvent> observer) {
        // cast ? to Sorter<SortingEvent> and add to list of observers.
        this.sorterObservers.add( observer);
    }

    // remove an observer.
    @Override
    public void removeObserver(Observer<SortingEvent> observer) {
        this.sorterObservers.remove( observer);
    }

    @Override
    public void notifyObservers(Subject<SortingEvent> subject, SortingEvent event) throws InterruptedException{

        // ignore request if empty.
        if (this.sorterObservers.isEmpty()) {
            return;
        }

        // graphics will be updated.
        for (Observer<SortingEvent> observer : this.sorterObservers) {
            observer.onNotify(subject, event);
        }
    }

   

}
