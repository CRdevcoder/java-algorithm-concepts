package utility.classes;

import utility.classes.Subject;

// Parameter E is the enum type of the event that the subject is notifying this observer of.
public interface Observer <E extends Enum<E>> {

    // subjects call this method to notify their observers. They send their reference too.
    // And uses enums to categorize the type of event that is being notified to the observer.
    public void onNotify( Subject<E> subject, E eventType);

}
