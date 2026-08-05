package utility.classes;

import utility.classes.Subject;

// Paramter T is the class type of the subject that the observer is observing.
// Parameter E is the enum type of the event that the subject is notifying this observer of.
public interface Observer <T extends Subject, E extends Enum<E>> {

    // subjects call this method to notify their observers. They send their reference too.
    // And uses enums to categorize the type of event that is being notified to the observer.
    public void onNotify(T subject, E eventType);

}
