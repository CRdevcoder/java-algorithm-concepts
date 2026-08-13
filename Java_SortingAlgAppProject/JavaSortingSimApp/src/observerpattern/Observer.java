package observerpattern;

import observerpattern.Subject;

// Note: before had <T extends Event<?>>, overcomplicated things!
// T is parameter for the type of Event being used by both Observer and Subject.
public interface Observer <T> {
    // subjects call this method to notify their observers. They send their reference too.
    // !!! But how do I tell it what value the event is storing?
    // try passing it in on notify?
    public void onNotify( Subject<T> subject, T eventType);
}
