package utility.classes;

import java.lang.reflect.Array;
import java.util.ArrayList;

import sortingalgorithms.classes.Sorter;

// STATUS: Beta 1
// TrailRound method will require changes, supposed to return Arraylist of trail time data.

// Purpose:
// Puts inputed algorithm through test trails to measure it's time complexity.
// Class that will receive a sorting algorithm object, then time it's speed.
// Uses a Sorter Object

public class SortingTimer<E extends Comparable<E>> {

    // construct it with algorithm object.
    Sorter algorithm;

    // number of tests it does per size.
    public SortingTimer(Sorter algorithm)
    {
        this.algorithm = algorithm;
    }

    // setter
    public void setAlgorithm(Sorter algorithm)
    {
        this.algorithm = algorithm;
    }
    
    // returns elapsed nanosecounds of algorithm.
    public long testTrail(ArrayList<E> list)
    {
        // start timer.
        long startTime = System.nanoTime();

        // sort list.
        algorithm.sortList(list);

        // stop timer after sorting finished.
        long endTime = System.nanoTime();

        return (endTime - startTime);
    }

    // provide arraylist and number of test trails, prints out time results.
    public long trailRound(int numTrails, ArrayList<E> list)
    {
        long totalNanoSec = 0;
        // each iteration serves as one test trail.
        for (int i = 0; i < numTrails; i++) {
            ArrayList<E> unsortedList = new ArrayList<>();
            unsortedList.addAll(list);
            //System.out.println(unsortedList);

            // times the time it takes to sort the list in nano secounds.
            long tc = testTrail(unsortedList);
            //System.out.println(unsortedList);

            totalNanoSec =+ tc;
            // print trail run in nano secounds and normal secounds.
            System.out.println( "TRAIL " + (i + 1) + " - \t" + (tc) + " ns" + " |\t" + (tc/(1E+9)) + " secs");
        }
        // average time in nano secounds.
        long averageTime =  totalNanoSec/numTrails;
        System.out.println("AVG. TIME: " + averageTime + " ns");
        return averageTime;
    }




}
