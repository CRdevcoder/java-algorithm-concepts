package observerpattern;

// constructed and sent to observers within Sorter classes. 
// delievers selected indexes to Animation Unit.
public class SortingEvent implements Event<int[]>{

    // elements selected by Sorter.
    private int[] selectedIndexes;
    // DurationType enum. how long the animation will pause
    private DurationType duration;
    // If array has been changed or accessed.
    private ActionType action;

    // MODIFYING PAUSE DURATION:
    // when an algorithm is scanning through an array. 
    // I want the pause to be shorter.
    // I want the pause to be longer when it actually changes the array.

    // two enums that represent the duration of a step.
    // Small step is a fraction of a larger step.
    public enum DurationType{
        FULL_STEP, SMALL_STEP
    }
    // moving elements vs selecting them.
    // to distinguish between moving elements and merely comparing elements or searching the array. 
    public enum ActionType{
        MOVING, SCANNING
    }

    // Assumes long step and elements have been moved.
    public SortingEvent( int... selectedIndexes)
    {
        // assume long pause for duration.
        this(DurationType.FULL_STEP, ActionType.MOVING, selectedIndexes);
    }

    public SortingEvent(DurationType duration,ActionType action, int ...selectedIndexes){
        // deep copy array.
        this.selectedIndexes = new int[selectedIndexes.length];
        // type of pause
        this.duration = duration;
        // use to determine type of coloring for graph bars.
        this.action = action;
        //copy array arg to array member.
        for(int i = 0; i < this.selectedIndexes.length; i++)
        {
            this.selectedIndexes[i] = selectedIndexes[i];
        }
    }
    // get duration type enum.
    public DurationType getDurationType(){
        return duration;
    }
    // get action.
    public ActionType getActionType(){
        return action;
    }

    // returns selected indexes.
    public int[] getData(){
        // returns deep copy of int[]
        return selectedIndexes.clone();
    }
}
