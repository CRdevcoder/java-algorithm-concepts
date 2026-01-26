package utility.classes;

import java.util.ArrayList;

import sortingalgorithms.classes.MergeSort;

// functions for organizing comparable classes, or preparing them for organization.
public class ComparableHelper {
// Idea: function that compares arraylist of comparable class methods.
// then stores them into an ordered pair node. 
// with their corresponding int value relative to the rest of the list.

    // sorts array of comparable elements, then returns a TagNode ArrayList.
    public static <E extends Comparable<E>> ArrayList<TagNode<E>> sortToTagList( ArrayList <E> list)
    {
        // Step 1: create copy of arraylist param arg.
        ArrayList<E> copyList = new ArrayList<>();
        // copy list.
        copyList.addAll(list);

        // Step 2: create tagged list.
        ArrayList<TagNode<E>> tagList = new ArrayList<>();

        // Step 3: sort copy list. on ascending order.
        MergeSort sorter = new MergeSort();
        sorter.sortList(copyList); // sorts copylist.

        // Step 4:
        // create tag nodes, their "tag numbers" are based on the order.
        // 1st nodes key should be 1, 2nd is 2, etc.
        // if an element is "equal" to the one before it, its tag should also be equal.
        int currTag = 1; // current tag number to be assigned.
        for (int i = 0; i < copyList.size(); i++) {
            
            Integer assignedTag = currTag;
            E value = copyList.get(i); // get object reference.
            TagNode<E> newNode = new TagNode<E>(assignedTag, value); // store tag and object in TagNode.
            // add to tag list.
            tagList.add(newNode);

            // Incrementing current tag number.
            // check if next element exists.
            if( (i+1) < copyList.size()) 
            {
                boolean isNextItemEqual = copyList.get(i).compareTo(copyList.get(i + 1)) == 0;
                if(!isNextItemEqual) // if not, increment currTag for next element.
                {
                    currTag++;
                }
            }
        }
        
        // return ref to tag list.
        return tagList;
    }

    // converts arrayList to TagList, but with the same order of the submitted array.
    // use if you want an unsorted tagNode list.
    // reconstructs the given arraylist, into one made up of TagNodes.
    public static <E extends Comparable<E>> ArrayList<TagNode<E>> convertToTagList( ArrayList <E> list)
    {
        ArrayList<TagNode<E>> sortedTagList = ComparableHelper.sortToTagList(list);

        // recreate the tag list, but in the original order of list parameter.
        ArrayList<TagNode<E>> originalTagList = new ArrayList<>();

        // Iterate through each item in tagged sorted list.
        // for each tag node, iterate through original list once, 
        // AND compare the Node's value to each list element.
        // add elements from  sortedTagList list to originalTagList, according to order in original list.
        for (int i = 0; i < list.size(); i++) {
            
            // k = index position in sorted list.
            E item = list.get(i);
            for (int k = 0; k < sortedTagList.size(); k++) {
                E nodeVal = sortedTagList.get(k).getValue();
                // if equal, add the node to originalTagList.
                if( item.compareTo(nodeVal) == 0)
                {
                    originalTagList.add(sortedTagList.get(k));
                    break; // stop loop and continue to avoid duplicates.
                }
            }
        }
        
        return originalTagList;
    }


}
