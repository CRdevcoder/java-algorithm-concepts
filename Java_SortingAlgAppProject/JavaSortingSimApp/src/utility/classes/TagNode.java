package utility.classes;

public class TagNode<V> extends PairNode<Integer, V> implements Comparable<Integer>{

    // for organizing comparable objects in a list, when their not numbers.
    public TagNode( int tag, V value)
    {
        super((Integer)tag,value);
    }

    // RETURNS 0 if numbers are equal. 
    // RETURNS negative number if caller's key is less than otherTag parameter.
    // RETURNS positive number if caller's key is greater than otherTag.
    @Override
    public int compareTo(Integer otherTag) {
        
        Integer keyTag = this.getKey();

        return keyTag.compareTo(otherTag);
    }

    public String toString()
    {
        return "TageNode = Tag: " + this.getKey() + " \tValue:" + this.getValue();
    }
}
