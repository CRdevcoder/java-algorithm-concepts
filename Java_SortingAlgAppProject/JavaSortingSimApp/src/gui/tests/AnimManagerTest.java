package gui.tests;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.AnimationManager;
import gui.AppWindow;
import sortingalgorithms.classes.BubbleSort;
import utility.classes.NumArrayGenerator;

import java.awt.Color;

public class AnimManagerTest {

    public static void main(String[] args) {
        System.out.println("TESTING ANIMATION MANAGER:");

        NumArrayGenerator ng = new NumArrayGenerator(237, 1,30);

        // create focus array.
        ArrayList<Integer> focusArray1 = new ArrayList<>(Arrays.asList(20,1,10,3,8,2,4,5,6));
        ArrayList<Integer> focusArray2 = ng.generateIntList(20);

        // sorters
        BubbleSort<Integer> bub1 = new BubbleSort<>();
        BubbleSort<Integer> bub2 = new BubbleSort<>();

        // create animation manager.
        AnimationManager animManager1 = new AnimationManager(focusArray1, bub1,500);
        // 2nd one.
        AnimationManager animManager2 = new AnimationManager(focusArray2, bub2,100);
        // set bar color to green.
        animManager2.setGraphColor(Color.GREEN);

        AppWindow window = new AppWindow("Animation Manager Test", 800, 600, true);

        JPanel mainContainer = new JPanel();
        BoxLayout boxLayout = new BoxLayout(mainContainer, BoxLayout.Y_AXIS);
        mainContainer.setLayout(boxLayout);

        window.add(mainContainer);
        // add bar graph guis to main container.
        mainContainer.add(animManager1.getBarGraphGui());
        mainContainer.add(animManager2.getBarGraphGui());

        // begin sorter.
        bub1.sortList(focusArray1);
        bub2.sortList(focusArray2);

    }
    
}
