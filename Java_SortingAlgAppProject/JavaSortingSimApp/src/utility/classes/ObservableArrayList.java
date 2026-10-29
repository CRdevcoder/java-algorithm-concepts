package utility.classes;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

//Arraylist that animates itself. 
public class ObservableArrayList<E> extends ArrayList<E> {

    @Override
    public E set(int index, E element) {
        // TODO Auto-generated method stub
        fireEvent("get " + index);
        return super.set(index, element);
    }

    private void fireEvent(String commandString)
    {
        ActionEvent change = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, commandString);
    }

}
