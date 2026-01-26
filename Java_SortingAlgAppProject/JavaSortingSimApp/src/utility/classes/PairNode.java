package utility.classes;

// stores two objects, one "key" and the "value"
public class PairNode<K,V> {

    // plan: key value is like a "tag" or "label" for the V value object.
    private K key;
    private V value;

    // stores references to both key and value objects.
    public PairNode( K key, V value){
        this.key = key;
        this.value = value;
    }

    // getters.
    public K getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }

    // setters.
    public void setKey(K newKey)
    {
        this.key = newKey;
    }

    public void setValue(V newValue)
    {
        this.value = newValue;
    }

}
