package sortingalgorithms.classes;
import java.util.ArrayList;

import observerpattern.SortingEvent;
import observerpattern.Subject;

import java.lang.Comparable;

// NOTE: Sorter Interface for algorithms.
public interface Sorter <T extends Comparable<T>> extends Subject<SortingEvent> {

    // Takes an ArrayList, copies it, sorts it, then returns the sorted list.
    // Accepts DataTypes that implement Comparable Interface.
    public ArrayList<T> sortList(ArrayList<T> list) throws InterruptedException;

    // Idea: get selected index method. Returns index that is modified/swapped. This will be used to highlight the element in the GUI.

}
