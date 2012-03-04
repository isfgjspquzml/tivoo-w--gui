package XMLNode;

/**
 * XML Node object for "Date" tag
 * Date tag includes all time information (year, month, day, hour, minutes, seconds)
 * @author Andrew, Tianyu
 */
import java.util.regex.Matcher;

import event.Event;


public class XMLNodeDate extends XMLNode {

    
    public void updateEvent(Event event, String tagInfo) 
    {
        Matcher yearMonDayMatcher = getYearMonDayMatcher(tagInfo);
        Matcher hourMinSecMatcher = getHourMinSecMatcher(tagInfo);
        
        String monthDayYearString = findMatch(yearMonDayMatcher);
        String hourMinSecString = findMatch(hourMinSecMatcher);
        
        setDateInformation(yearMonDayMatcher, event, monthDayYearString);
        setTimeInformation(hourMinSecMatcher, event, hourMinSecString);
    }
    
    
}
