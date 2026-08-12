package observerpattern;

import observerpattern.Observer;

// E is the enum type of the event that the subject is notifying this observer of.
public interface Subject<E extends Enum<E>> {

    // use hashtables to store observers? No.

    // add given observer to the list of observers.
    public void addObserver(Observer<E> observer);

    // remove given observer from the list of observers.
    public void removeObserver(Observer<E> observer);

    // iterate through each observer and call their onNotify method.
    public void notifyObservers();
}
