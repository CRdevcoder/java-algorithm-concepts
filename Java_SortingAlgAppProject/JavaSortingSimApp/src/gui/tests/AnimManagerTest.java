package gui.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.AnimationManager;
import gui.AppWindow;
import gui.GraphListDisplay;
import sortingalgorithms.classes.BubbleSort;
import sortingalgorithms.classes.MergeSort;
import utility.classes.NumArrayGenerator;

import java.awt.Color;

public class AnimManagerTest {

    public static void main(String[] args) {
        System.out.println("TESTING ANIMATION MANAGER:");

        NumArrayGenerator ng = new NumArrayGenerator(237, 1,30);

        // create focus array.
        ArrayList<Integer> focusArray1 = new ArrayList<>(Arrays.asList(20,1,10,3,8,2,4,5,6,8,15,4,7));
        ArrayList<Integer> focusArray2 = ng.generateIntList(20);

        // sorters
        BubbleSort<Integer> bub1 = new BubbleSort<>();
        MergeSort<Integer> merge1 = new MergeSort<>();

        // Jpanel gui.
        GraphListDisplay graphListDisplay = new GraphListDisplay();

        // create animation manager.
        AnimationManager animManager1 = new AnimationManager(focusArray1, bub1,500);
        // 2nd one.
        AnimationManager animManager2 = new AnimationManager(focusArray2, merge1,500);
        // set bar color to green.
        animManager2.setGraphColor(Color.GREEN);

        // Add the bar graph guis into the graph display.
        graphListDisplay.addGraph(animManager1);
        graphListDisplay.addGraph(animManager2);

        AppWindow window = new AppWindow("Animation Manager Test", 800, 600, true);

        JPanel mainContainer = new JPanel();
        BoxLayout boxLayout = new BoxLayout(mainContainer, BoxLayout.Y_AXIS);
        mainContainer.setLayout(boxLayout);

        window.add(mainContainer);
        // add bar graph guis to main container.
        mainContainer.add(graphListDisplay);
        //mainContainer.add(animManager2.getBarGraphGui());

        // execute the sorting algorithms in seperate threads to run both animations asynchronously.
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // begin sorter.
        // Try executing these in seperate threads,so the thread delays dont block each other.
        executor.execute(() -> bub1.sortList(focusArray1));
        executor.execute(() -> merge1.sortList(focusArray2));

        executor.shutdown();
    }
    
}
