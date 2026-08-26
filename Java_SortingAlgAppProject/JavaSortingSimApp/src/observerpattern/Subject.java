package observerpattern;

import observerpattern.Observer;

// T is the type of the event that the subject is notifying this observer of.
// T is parameter for the type of Event being used by both Observer and Subject.
public interface Subject<T> {

    // use hashtables to store observers? No.

    // add given observer to the list of observers.
    public void addObserver(Observer<T> observer);

    // remove given observer from the list of observers.
    public void removeObserver(Observer<T> observer);

    // iterate through each observer and call their onNotify method.
    public void notifyObservers( Subject<T> subject, T event) throws InterruptedException;
}
