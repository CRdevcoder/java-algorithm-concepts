package animation.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import animation.AnimationUnit;
import gui.AppWindow;
import gui.GraphListDisplay;
import sortingalgorithms.classes.BubbleSort;
import sortingalgorithms.classes.MergeSort;
import sortingalgorithms.classes.SorterFactory.Algorithm;
import utility.classes.NumArrayGenerator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimUnitTest implements ActionListener{

    public JButton resetButton;
    public AnimationUnit animManager1;
    public AnimationUnit animManager2;

    // constructor
    AnimUnitTest(){
        resetButton = new JButton("Reset and Run Animation");
        resetButton.addActionListener(this);

        NumArrayGenerator ng = new NumArrayGenerator(237, 1,30);

        // create focus array.
        ArrayList<Integer> focusArray1 = new ArrayList<>(Arrays.asList(20,1,10,3,8,2,4,5,6,8,15,4,7));
        ArrayList<Integer> focusArray2 = ng.generateIntList(20);

        // set object variables: animation units that will help handle animations.
        // create animation manager.
        this.animManager1 = new AnimationUnit(focusArray1, Algorithm.BUBBLE,500);
        // 2nd one.
        this.animManager2 = new AnimationUnit(focusArray2, Algorithm.MERGE,500);
        animManager2.setGraphColor(Color.GREEN);
    }

    public static void main(String[] args) {
        System.out.println("TESTING ANIMATION MANAGER:");

        // Jpanel gui.
        GraphListDisplay graphListDisplay = new GraphListDisplay();

        // create.
        AnimUnitTest tester = new AnimUnitTest();

        // Add the bar graph guis into the graph display.
        graphListDisplay.addGraph(tester.animManager1.getBarGraphGui());
        graphListDisplay.addGraph(tester.animManager2.getBarGraphGui());

        AppWindow window = new AppWindow("Animation Manager Test", 800, 600, true);

        JPanel mainContainer = new JPanel();
        BoxLayout boxLayout = new BoxLayout(mainContainer, BoxLayout.Y_AXIS);
        mainContainer.setLayout(boxLayout);

        window.add(mainContainer);
        // add bar graph guis to main container panel.
        mainContainer.add(graphListDisplay);
        // add button.
        mainContainer.add(tester.resetButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == resetButton){
            animManager1.reset();
            animManager2.reset();

        // execute the sorting algorithms in seperate threads to run both animations asynchronously.
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // begin sorter.
        // Try executing these in seperate threads,so the thread delays dont block each other.
        // must ask animation units to begin sorting their arrays.
        // NOTE: what happens to the previously running threads if this button is pressed twice?
        executor.execute(() -> animManager1.beginSort());
        executor.execute(() -> animManager2.beginSort());

        executor.shutdown();

        }
        
    }
    
}
