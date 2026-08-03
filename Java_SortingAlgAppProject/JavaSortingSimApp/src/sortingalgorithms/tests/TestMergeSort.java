package sortingalgorithms.tests;

import java.util.ArrayList;
import java.util.Arrays;

import sortingalgorithms.classes.MergeSort;
import utility.classes.LetterGenerator;

public class TestMergeSort {

    public static void main(String[] args) {

        System.out.println("TESTING MERGE SORTER:");
        // Letter Generator.
        LetterGenerator gl = new LetterGenerator(90583623);
        gl.setRange('A','F');
        ArrayList<Character> list = gl.generateLetterList(20);
        
        // Creating MergeSort object, setting print mode to true.
        MergeSort<Character> ms = new MergeSort<Character>(true);

        // Sorting random List of Chars
        System.out.println("\n(SORTING CHAR ARRAY)");
        System.out.println("Unsorted Char Array: " + list + "\n");

        ms.sortList(list);

        System.out.println("Sorted Char Array: " + list);

        // Sorting List of Integers.
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(7,1,9,3,8,2,4,5,6));
        System.out.println("\n(SORTING NUMBER ARRAY)");
        System.out.println("Unsorted Num Array: " + numList + "\n");

        MergeSort<Integer> msNum = new MergeSort<Integer>(true);
        msNum.sortList(numList);

        System.out.println("Sorted Num Array: " + numList);

        System.out.println("\n(END OF TEST)");

    }

}
