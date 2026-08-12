package sortingalgorithms.classes;
import java.util.ArrayList;

import observerpattern.Subject;

import java.lang.Comparable;
import utility.classes.Subject;

// NOTE: Sorter Interface for algorithms.
public interface Sorter <T extends Comparable<T>> extends Subject<Sorter.SortingEvent> {

    // Takes an ArrayList, copies it, sorts it, then returns the sorted list.
    // Accepts DataTypes that implement Comparable Interface.
    public ArrayList<T> sortList(ArrayList<T> list);

    public enum SortingEvent {
        REDRAW_ARRAY_PLAIN, // redraw the array and keep all bars the same color.
        ELEMENT_SELECTED, // highlight the selected element in the array.
        ELEMENT_MOVED,
        ELEMENT_SWAPPED
    }

    // Idea: get selected index method. Returns index that is modified/swapped. This will be used to highlight the element in the GUI.

}
