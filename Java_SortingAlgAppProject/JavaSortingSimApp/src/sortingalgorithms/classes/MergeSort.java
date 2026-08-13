package sortingalgorithms.classes;

import java.util.ArrayList;

import observerpattern.Observer;
import observerpattern.Subject;
import observerpattern.SortingEvent.ActionType;
import observerpattern.SortingEvent.DurationType;
import observerpattern.SortingEvent;

// subject class.
public class MergeSort<T extends Comparable<T>> implements Sorter<T> {

    boolean printMode; // set to true if you want methods to print to console.

    // Arraylist of observers.
    private ArrayList<Observer<SortingEvent>> observerList;

    // default constructor
    public MergeSort()
    {
        this.printMode = false;
        this.observerList = new ArrayList<>();
    }

    public MergeSort(boolean printMode )
    {
        this.printMode = printMode;
    }

    // setter.
    public void setPrintMode(boolean printMode)
    {
        this.printMode = printMode;
    }
    // getter.
    public boolean getPrintMode()
    {
        return this.printMode;
    }

    // sortList method. calls recursive sort.
    public ArrayList<T> sortList( ArrayList<T> listArg)
    {
        // Enter first and last index of list.
        recursiveMergeSort(listArg, 0, listArg.size() - 1);
        return listArg;
    }

    private void recursiveMergeSort( ArrayList<T> a, int first, int last)
    {
        if (printMode) {
            // subarray length
            int subLength = Math.abs(first - last);
            //prints param info:
            System.out.println( "Rec MergeSort CALL PARAM: first: " + first + " | last: " + last + " | sub array length: " + subLength);
        }
        // calculate mid point.
        int mid = (first + last)/2;
        
        // Base case - when last index is equal to first index. 
        // (When receive single element sub array)
        if( first < last)
        {
            // Phase 1 - dividing into sub arrays
            recursiveMergeSort(a, first, mid); // leftmost sub array
            recursiveMergeSort(a, mid + 1, last); // rightmost sub array

            // Phase 2 - Post Base Case - begin sorting subarrays by merging them together
            // Ran when recursive calls are returning
            merge(a,first,mid,last);
            
            // notify observers that the array has changed. (merged)
            notifyObservers(this, new SortingEvent(first,mid,last)); 
        }
        else // when one element array
        {
            return; // base case is met. Begin returning.
        }
    }

    // Uses midpoint parameter to divide the (a) list into 2 sub arrays.
    // Merges two adjacent sub arrays that are within the index range (first and last).
    private void merge(ArrayList<T> a, int first, int mid, int last)
    {
        if (printMode) {
            //subarray length
            int subLength = Math.abs(first - last);
            // prints param info:
            System.out.println( "MERGE CALL PARAM: " + a + "_ first: " + first + "_ mid: " + mid + "_ last: " + last + "_ sub array length: " + subLength + "\n");
        }
        int subLength = Math.abs(first - last);

        int leftSubStart, leftSubEnd, rightSubStart, rightSubEnd;
        // left sub array (1st) index range.
        leftSubStart = first;
        leftSubEnd = mid;
        // right sub array (2nd) index range.
        rightSubStart = mid + 1;
        rightSubEnd = last;
        // temp ArrayList
        ArrayList<T> tempList = new ArrayList<>();

        // While both sub Arrays haven't been looped through fully, compare elements from both.
        // after each while loop iteration, the smaller element between sub arrays will be added to temp array.
        while( (leftSubStart <= leftSubEnd) && (rightSubStart <= rightSubEnd))
        {
            T leftItem = a.get(leftSubStart);
            T rightItem = a.get(rightSubStart);

            // send scanning notification
            notifyObservers(this, new SortingEvent(DurationType.SMALL_STEP,ActionType.SCANNING,leftSubStart,rightSubStart));

            // returns negative if leftItem less than rightItem.
            if(leftItem.compareTo(rightItem) <= 0)
            {
                tempList.add(leftItem); // add smaller item to list.
                leftSubStart++; // Move index foward so we don't add smaller item again.
            }
            else{ // adding to right subArray
                tempList.add(rightItem);
                rightSubStart++;
            }
        }

        // COPY LEFT OVER elements in left sub array
        while ((leftSubStart <= leftSubEnd)) {
            T leftItem = a.get(leftSubStart);
            tempList.add(leftItem);
            leftSubStart++;
        }

        // Copy left over elements in right sub array.
        while(rightSubStart <= rightSubEnd)
        {
            T rightItem = a.get(rightSubStart);
            tempList.add(rightItem);
            rightSubStart++;
        }

        // empty parameter arrayList, then copy temp array into it.
        int index = first;
        for (int i = 0; i < tempList.size(); i++) {
            a.set(index, tempList.get(i));
            index++;
        }

        return;// FINISHED
    }

    @Override
    public void addObserver(Observer<SortingEvent> observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer<SortingEvent> observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers( Subject<SortingEvent> subject, SortingEvent event ) {
        for (Observer<SortingEvent> observer : observerList) {
            observer.onNotify(subject, event);
        }
    }

}
