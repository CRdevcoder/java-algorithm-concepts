package utility.classes;

import utility.classes.Observer;

public interface Subject<E extends Enum<E>> {

    // use hashtables to store observers? No.

    // add given observer to the list of observers.
    public void addObserver(Observer<? extends Subject<E>,E> observer);

    // remove given observer from the list of observers.
    public void removeObserver(Observer<? extends Subject<E>,E> observer);

    // iterate through each observer and call their onNotify method.
    public void notifyObservers();
}
