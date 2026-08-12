package sortingalgorithms.classes;

import java.util.ArrayList;
import java.util.List;

import observerpattern.Observer;
import observerpattern.Subject;

import java.lang.Character;

// implements Bubble Sort

// subject.
public class BubbleSort<T extends Comparable<T>> implements Sorter<T> {

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
    
    public ArrayList<T> sortList(ArrayList<T> listArg) {

        // Make deep copy of arrayList?
        /*
        ArrayList<Comparable> copyList = new ArrayList<>();
        for (Comparable c : listArg) {
            copyList.add(c);
        }
        */

        int length = listArg.size();
        boolean swapped = false; // false if passes through array without swapping.

        // i is current iteration, j is current index.
        // outter loop (i)
        for (int i = 0; i < length - 1; i++) {
            swapped = false;
            // inner loop(j)
            for(int j=0; j < length - i - 1; j++)
            {
                // returns num greater than 0 if j bigger than j+1
                if(listArg.get(j).compareTo( listArg.get(j+1)) > 0)
                {
                    swap(j,listArg);
                    //System.out.println( i + " - " + j + ": " + listArg + " - Swapped (" + j + ") " + listArg.get(j+1) + " with (" + (j+1) + ") " + listArg.get(j) + "\n");
                    swapped = true;
                }
            }
            if(!swapped)
            {
                //System.out.println(i + " : No More Swaps, ENDING SORT");
                break;
            }
        }

        return listArg;
    }
    

    // Single bubble sorting pass through given ArrayList.
    /*private void pass(ArrayList<Comparable> list, int sortedIndex)
    {

    }
    */

    // swaps one element with it's rightwards neighbor, given it's arrayList and index.
    private <T extends Comparable<T>> void swap(int index,ArrayList<T> list)
    {
        // store element in temp
        T temp = list.get(index);
        // swapping elements
        list.set(index,list.get(index + 1));
        list.set(index + 1, temp);

        System.out.println("Notify Observers");
        notifyObservers();
    }


    // Subject interface methods for Observer pattern.
    //Add.
    @Override
    public void addObserver(Observer<SortingEvent> observer) {
        // cast ? to Sorter<SortingEvent> and add to list of observers.
        this.sorterObservers.add( observer);
    }

    // remove.
    @Override
    public void removeObserver(Observer<SortingEvent> observer) {
        this.sorterObservers.remove( observer);
    }

    // notify the observer.
    @Override
    public void notifyObservers() {

        if (this.sorterObservers.isEmpty()) {
            return;
        }

        // graphics will be updated.
        for (Observer<SortingEvent> observer : this.sorterObservers) {
            observer.onNotify(this, SortingEvent.REDRAW_ARRAY_PLAIN);
        }

        
    }

}
