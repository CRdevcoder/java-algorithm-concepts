package sortingalgorithms.classes;
import java.util.ArrayList;
import java.lang.Comparable;

// NOTE: Sorter Interface for algorithms.
public interface Sorter <T extends Comparable<T>> {

    // Takes an ArrayList, copies it, sorts it, then returns the sorted list.
    // Accepts DataTypes that implement Comparable Interface.
    public ArrayList<T> sortList(ArrayList<T> list);

}
