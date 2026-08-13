package observerpattern;

// object used to pass data.
public interface Event<T> {

    // retreive data relevant to event.
    public T getData();
    
}
