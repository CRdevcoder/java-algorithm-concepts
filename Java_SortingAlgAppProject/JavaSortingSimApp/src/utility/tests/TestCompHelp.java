package utility.tests;

import java.util.ArrayList;
import java.util.Arrays;

import utility.classes.ComparableHelper;
import utility.classes.LetterGenerator;
import utility.classes.TagNode;

public class TestCompHelp {

    public static void main(String[] args) {
        
        // Integer list test.
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(7,1,9,3,8,5,2,4,5,6));
        LetterGenerator letListMaker = new LetterGenerator(7893);
        ArrayList<Character> letList = letListMaker.generateLetterList(13);

        // "tagged" lists. normal arrays "converted" to arrays with tag list.
        ArrayList<TagNode<Integer>> tNums = ComparableHelper.sortToTagList(numList);
        ArrayList<TagNode<Character>> tLets = ComparableHelper.sortToTagList(letList);

        // should all be sorted.
        System.out.println("NUMBER TEST:");
        for (TagNode<Integer> node : tNums) {
            System.out.println(node);
        }

        System.out.println("\nLETTER TEST");
        for (TagNode<Character> node : tLets) {
            System.out.println(node);
        }

        // testing convertToTagList. see if it can reconstruct arraylist order but with TagNodes.
        System.out.println("\nRecreating Unsorted Letter ArrayList With TagNodes:");
        tLets = ComparableHelper.convertToTagList(letList);

        for (TagNode<Character> node : tLets) {
            System.out.println(node);
        }

        System.out.println("ORIGNAL LETTER LIST ORDER:");
        for(Character let : letList)
        {
            System.out.print(let + ",");
        }

    }

}
