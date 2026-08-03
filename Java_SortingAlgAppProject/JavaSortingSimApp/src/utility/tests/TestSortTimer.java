package utility.tests;

import java.util.ArrayList;

import sortingalgorithms.classes.BubbleSort;
import sortingalgorithms.classes.MergeSort;
import utility.classes.LetterGenerator;
import utility.classes.SortingTimer;

// Times the total sort time of classes that implement the SortAlgorithm interface.
public class TestSortTimer {

    public static void main(String[] args) {
        

        LetterGenerator gen1 = new LetterGenerator(17658);
        BubbleSort<Character> bubSort = new BubbleSort<>();
        // create char list.
        ArrayList<Character> list1 = gen1.generateLetterList(20);
        System.out.println("Unsorted list :" + list1);

        // creating sortingTimer. with bubbleSort object.
        SortingTimer<Character> timer = new SortingTimer<>(bubSort);
        long tcomplexity = timer.testTrail(list1);

        System.out.println("Sorted list: " + list1);

        System.out.println("Bubble Sort TIME COMPLEXITY (nano secounds): " + tcomplexity);

        // Test with bubble sort.
        System.out.println("BUBBLE SORT TIMES:");
        // Average bubble sort times (nano secounds)
        ArrayList<Long> bubbleTimes = testTimerTrailRounds(timer,198654279);
        

        // testing merge sort times.
        // Merge sort times:
        MergeSort mergeSort = new MergeSort();
        timer.setAlgorithm(mergeSort);

        System.out.println("MERGE SORT TIME TEST:");
        ArrayList<Long> mergeTimes = testTimerTrailRounds(timer, 198654279);

        // printing out timed results.
        System.out.println("\nAvg BubbleSort Time: \t" + bubbleTimes + "\nAvg Merge Time: \t" + mergeTimes);

    }

    // tests the trailRound method of SortingTimer
    private static ArrayList<Long> testTimerTrailRounds(SortingTimer <Character>timer, int seed)
    {
        // Store average results:
        ArrayList<Long> avgTimeList = new ArrayList<>();

        LetterGenerator gen = new LetterGenerator(seed);
        
        // short list
        ArrayList<Character> shortList = gen.generateLetterList(1000);
        // medium list
        ArrayList<Character> midList = gen.generateLetterList(3000);
        // huge list.
        ArrayList<Character> longList = gen.generateLetterList(5000);

        // run trail rounds.
        System.out.println("1000 elem:");
        avgTimeList.add(timer.trailRound(20,shortList));
        
        System.out.println("3000 elem:");

        avgTimeList.add(timer.trailRound(20,midList));
                
        System.out.println("5000 elem:");
        avgTimeList.add(timer.trailRound(20,longList));

        return avgTimeList;
    }

}
