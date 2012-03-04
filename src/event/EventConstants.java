package event;

/**
 * Event interface that stores constants used by Event objects and XMLNode objects
 * @author Andrew, Tianyu
 */
public interface EventConstants {

    static final String SUNDAY_STRING = "Sunday";
    static final String MONDAY_STRING = "Monday";
    static final String TUESDAY_STRING = "Tuesday";
    static final String WEDNESDAY_STRING = "Wednesday";
    static final String THURSDAY_STRING = "Thursday";
    static final String FRIDAY_STRING = "Friday";
    static final String SATURDAY_STRING = "Saturday";
    
    static final String[] myDays = { 
        SUNDAY_STRING,
        MONDAY_STRING,
        TUESDAY_STRING,
        WEDNESDAY_STRING,
        THURSDAY_STRING,
        FRIDAY_STRING,
        SATURDAY_STRING
     };
    
    static final String NULL_TITLE = "NO_TITLE";
    static final String NULL_DESCRIPTION = "NO_DESCRIPTION";
    static final String NULL_AUTHOR = "NO_AUTHOR";
    static final String NULL_START_DAY = "NO_START_DAY";
    static final String NULL_END_DAY = "NO_END_DAY";

    static final int TITLE_INDEX = 0;
    static final int DESCRIPTION_INDEX = 1;
    static final int AUTHOR_INDEX = 2;
    static final int START_DAY_INDEX = 3;
    static final int END_DAY_INDEX = 4;
    
    static final int THIS_DATE_BEFORE = -1;
    static final int THIS_DATE_AFTER = 1;
    static final int THIS_DATE_EQUAL = 0;
    
    static final String EVENT_TV_CLASS_NAME = "EventTV";
}
