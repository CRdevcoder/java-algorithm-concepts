package sortingalgorithms.classes;

// E is the class type of element being sorted by the algorithm.
public class SorterFactory<E extends Comparable<E>> {

    public enum Algorithm{
        BUBBLE, MERGE
    }

    // can return Sorters that sort: Integers, Doubles, Characters, etc.
    public Sorter<E> createSorter(Algorithm type){

        switch(type){
            case BUBBLE:
                return new BubbleSort<E>();
            case MERGE:
                return new MergeSort<E>();
        }

        // if none than return null.
        return null;
    }
    
}
