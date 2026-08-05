package gui.tests;

import java.util.ArrayList;
import java.util.Arrays;

import gui.AppWindow;
import gui.BarGraphGui;
import sortingalgorithms.classes.BubbleSort;

public class GraphDrawingTest {


    public static void main(String[] args) {
        AppWindow window = new AppWindow("Graph Drawing Test", 800, 600, true);
        
        ArrayList<Integer> testList = new ArrayList<>(Arrays.asList(5, 2, 9, 1, 5, 50));

        BarGraphGui barGraph = new BarGraphGui(testList);

        BubbleSort<Integer> bubbleSort = new BubbleSort<>();

        window.add(barGraph);
        window.setVisible(true);

        try {
            Thread.sleep(2000); // Wait for 2 seconds before starting the sort
            bubbleSort.sortList(testList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        barGraph.repaint();
    }

    
    
}
