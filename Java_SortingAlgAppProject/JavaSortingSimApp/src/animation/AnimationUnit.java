package animation;

import java.util.ArrayList;
import gui.BarGraphGui;
import gui.BarGraphGui.ColorType;
import observerpattern.Observer;
import observerpattern.Subject;
import observerpattern.SortingEvent.ActionType;
import observerpattern.SortingEvent.DurationType;
import observerpattern.SortingEvent;
import sortingalgorithms.classes.Sorter;
import sortingalgorithms.classes.SorterFactory;


import java.awt.Color;

import sortingalgorithms.classes.SorterFactory.Algorithm;;

// an observer class.
// it's subject is the sorting algorithm class.
// it will be notified when the sorting algorithm class changes the array.
// The sorting algorithm will call one of its methods to notify the observer that the array has changed.
// it is resposible for:
// 1 Commanding the bar graph gui to update its display.
// 2 Pausing the thread for a period of time to create the animation effect.
public class AnimationUnit implements Observer<SortingEvent> {
    

    // gui:
    private BarGraphGui barGraph; // the gui that draws the bar graph of the array that is being sorted.

    // copy of original array, it will not be sorted.
    // used to implement reset function.
    private ArrayList<Integer> focusArray;
    // copy of focus array. it will be sorted.
    private ArrayList<Integer> copyArray;

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

        // create shallow copy of focus array.
        this.focusArray = new ArrayList<Integer>(focusArray);
        // create another shallow copy, this will be sorted.
        this.copyArray = new ArrayList<Integer>(this.focusArray);
        // create the bar graph gui and pass the copy array to it.
        this.barGraph = new BarGraphGui(this.copyArray);
        
        // set the sorting algorithm.
        this.sortingAlgorithm = sortingAlgorithm;

        // add self as an observer to the sorting algorithm.
        this.sortingAlgorithm.addObserver(this);

        // set pause duration.
        this.pauseDuration = pauseDuration; // in milliseconds.
    }

    // commands the sorter to begin sorting the copyArray.
    public void beginSort(){
        this.sortingAlgorithm.sortList(copyArray);
    }

    // resets the copy array, by creating a new shallow copy of the current focus array.
    // intended to allow an animation to be reset from the beginning to be sorted again.
    public void reset(){
        this.copyArray = new ArrayList<Integer>(this.focusArray);
        // must update bar graph gui with new copyArray.
        this.barGraph.setFocusArray(this.copyArray);
        // redraw graph.
        updateGraph();
    }


    // setters
    public void setFocusArray(ArrayList<Integer> focusArray) {
        this.focusArray = new ArrayList<Integer>(focusArray);
        // set new copy array.
        this.copyArray = new ArrayList<Integer>(this.focusArray);
        this.barGraph.setFocusArray(this.focusArray);
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
    public void onNotify( Subject<SortingEvent> subject, SortingEvent event) {

        // clear data in BarGraph's hashTable.
        barGraph.clearSelectionData();

        // retreive index data from sorter. Pass it to Graph GUI.
        int[] selected = event.getData();
        // pass index data to graph. Tell graph how it should color the data. Graph clears previous data.
        if(event.getActionType() == ActionType.MOVING)
            this.barGraph.setSelectedBars(selected, ColorType.MOVED);
        else if(event.getActionType() == ActionType.SCANNING)
            // color scanned data bars.
            this.barGraph.setSelectedBars(selected, ColorType.SCANNED);

        // update the graph.
        updateGraph();
        // pause thread for period of time.
        try {
            // duration is chaged based on enum from SortingEvent.
            if(event.getDurationType() == DurationType.FULL_STEP)
                Thread.sleep(this.pauseDuration);
            else if(event.getDurationType() == DurationType.SMALL_STEP)
                // pause for fraction of the time.
                Thread.sleep(this.pauseDuration/2);
            else // default if neither (probably not reachable)
                Thread.sleep(this.pauseDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
