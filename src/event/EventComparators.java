
package event;

import java.util.Comparator;
import java.util.Date;

/**
 * Comparators to sort Events by title or date
 * @author James
 */

public class EventComparators {

    public static class TimeComparator implements Comparator<Event> {

        public int compare(Event event1, Event event2) {
            Date d1 = event1.getStartDate();
            Date d2 = event2.getStartDate();
            if (d1.after(d2)) {
                return 1;
            } else if (d1.before(d2)) {
                return -1;
            }
            return 0;
        }

    }
    
    public static class ReverseTimeComparator implements Comparator<Event> {

        public int compare(Event event1, Event event2) {
            Date d1 = (event1).getStartDate();
            Date d2 = (event2).getStartDate();
            if (d1.after(d2)) {
                return -1;
            } else if (d1.before(d2)) {
                return 1;
            }
            return 0;
        }
        
    }
    
    public static class TitleComparator implements Comparator<Event> {

        public int compare(Event event1, Event event2) {
            String t1 = (event1).getTitle();
            String t2 = (event2).getTitle();
            return t1.compareTo(t2);
        }
        
    }
    
    public static class ReverseTitleComparator implements Comparator<Event> {

        public int compare(Event event1, Event event2) {
            String t1 = (event1).getTitle();
            String t2 = (event2).getTitle();
            if (t1.compareTo(t2) > 0){
                return -1;
            }
            if (t1.compareTo(t2) < 0){
                return 1;
            }
            return 0;
        }
        
    }
}