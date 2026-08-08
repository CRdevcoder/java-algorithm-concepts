package gui;

import utility.classes.Observer;
import utility.classes.Subject;

import java.util.ArrayList;


import sortingalgorithms.classes.Sorter;
import sortingalgorithms.classes.SorterFactory;
import sortingalgorithms.classes.Sorter.SortingEvent;

import java.awt.Color;

import sortingalgorithms.classes.SorterFactory.Algorithm;;

// an observer class.
// it's subject is the sorting algorithm class.
// it will be notified when the sorting algorithm class changes the array.
// The sorting algorithm will call one of its methods to notify the observer that the array has changed.
// it is resposible for:
// 1 Commanding the bar graph gui to update its display.
// 2 Pausing the thread for a period of time to create the animation effect.
public class AnimationUnit implements Observer<Sorter.SortingEvent> {
    

    // gui:
    private BarGraphGui barGraph; // the gui that draws the bar graph of the array that is being sorted.
    // array that will be sorted.
    private ArrayList<Integer> focusArray;

    // sorting algorithm subject:
    private Sorter<Integer> sortingAlgorithm; // the sorting algorithm that is being used to sort the array.

    // pauseDuration.
    private int pauseDuration; // in milliseconds. 1000 milliseconds = 1 second.

    // constructor.
    public AnimationUnit(ArrayList<Integer> focusArray, Sorter<Integer> sortingAlgorithm) {
        this(focusArray, sortingAlgorithm, 1000); // default pause duration is 1 second.
    }

    // Pass an Algorithm enum into constructor to have the manager create a sorter within itself.
    // constructs a sorter using a factory.
    public AnimationUnit(ArrayList<Integer> focusArray, Algorithm algorithmType, int duration )
    {   
        SorterFactory<Integer> fac = new SorterFactory<Integer>();
        Sorter<Integer> alg = fac.createSorter(algorithmType);

        // pass to other constructor.
        this(focusArray, alg, duration);
    }

    public AnimationUnit(ArrayList<Integer> focusArray, Sorter<Integer> sortingAlgorithm, int pauseDuration) {
        // create focus array.
        this.focusArray = focusArray;
        // create the bar graph gui and pass the focus array to it.
        this.barGraph = new BarGraphGui(focusArray);
        // set the sorting algorithm.
        this.sortingAlgorithm = sortingAlgorithm;

        // add self as an observer to the sorting algorithm.
        this.sortingAlgorithm.addObserver(this);

        // set pause duration.
        this.pauseDuration = pauseDuration; // in milliseconds.
    }

    public void setFocusArray(ArrayList<Integer> focusArray) {
        this.focusArray = focusArray;
        this.barGraph.setFocusArray(focusArray);
    }

    public void setGraphColor(Color color) {
        this.barGraph.setBarColor(color);
    }

    // calls method to update the bar graph ui.
    public void updateGraph(){
        // call the bar graph gui to update the bar graph.
        this.barGraph.repaint();
    }

    public BarGraphGui getBarGraphGui() {
        return this.barGraph;
    }

    // color the 
    //public void updateGraph( int coloredIndex) {
    //}

    // we want AnimationUnit to be given the responsibility of both:
    // 1 updating the graph
    // 2 pausing for the animation.
    @Override
    public void onNotify( Subject<Sorter.SortingEvent> subject, SortingEvent eventType) {

        // update the graph.
        updateGraph();
        // pause thread for period of time.
        try {
            Thread.sleep(this.pauseDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
