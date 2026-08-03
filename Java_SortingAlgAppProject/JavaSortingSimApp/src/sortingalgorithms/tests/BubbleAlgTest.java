package sortingalgorithms.tests;

import java.util.ArrayList;
import java.util.Arrays;

import sortingalgorithms.classes.BubbleSort;
import utility.classes.LetterGenerator;

// Tests the BubbleSort class
public class BubbleAlgTest {

    public static void main(String[] args) {

        BubbleSort<Character> bsort = new BubbleSort<Character>();
        LetterGenerator generator = new LetterGenerator(3426);
        generator.setRange('A', 'C');

        // Generate Letter list from A to C.
        // Testing if bubble sort can handle multiple duplicates in a row.
        ArrayList<Character> ranList = generator.generateLetterList(10);

        System.out.println("Bubble Sorting List:\n" + ranList);

        bsort.sortList(ranList); // sorts list

        System.out.println("SORTED LETTER LIST:\n" + ranList + "\n");

        // Another Letter list: from A to H
        generator.setRange('A', 'H');
        ranList = generator.generateLetterList(10);
        System.out.println("2nd Bubble Sorting List:\n" + ranList);

        bsort.sortList(ranList);

        System.out.println("SORTED LETTER LIST:\n" + ranList + "\n");

        // Integer list test.
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(7,1,9,3,8,2,4,5,6));

        System.out.println("UNSORTED NUMBER LIST:\n" + numList + "\n");

        BubbleSort<Integer> bnumsort = new BubbleSort<Integer>();

        bnumsort.sortList(numList);
        System.out.println("SORTED NUMBER LIST:\n" + numList + "\n");


    }

}
