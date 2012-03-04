package event;
import java.util.ArrayList;

/**
 * MasterEventList object used to store ArrayLists of specific event types
 * @author Andrew, Tianyu
 */
public class MasterEventList {

    private static final int NUMBER_EVENT_TV_OBJECTS_OFFSET = -1;
    
    private static ArrayList<EventTV> myEventTVList;
    private static int myEventTVInstanceTracker;
    
    public MasterEventList () {
        myEventTVList = new ArrayList<EventTV>();
        myEventTVInstanceTracker = NUMBER_EVENT_TV_OBJECTS_OFFSET;
    }
    
    public static int getEventTVInstanceTracker () {
        return myEventTVInstanceTracker;
    }
    
    public static void incrementEventTVInstanceTracker() {
        ++myEventTVInstanceTracker;
    }
    
    public static ArrayList<EventTV> getEventTVList () {
        return myEventTVList;
    }
    
    public static EventTV getEventTV () {
        return myEventTVList.get(myEventTVInstanceTracker);
    }
    
    public static void addEventTV (EventTV event) {
        myEventTVList.add(event);
    }
}
