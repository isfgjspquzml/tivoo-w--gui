package XMLNode;

import event.Event;

/**
 * XML Node object for "Time" tag
 * Used in parsing GoogleCal summary information 
 * Summary represents year, month, day as String (i.e. Thu, Mar 1, 2012)
 * @author Andrew, Tianyu
 */
public class XMLNodeVerboseYearMonthDay extends XMLNode {

    
    public void updateEvent(Event event, String tagInfo) 
    {
        String[] yearMonthDay = parseYearMonthDay(tagInfo);
        
        setDateYearMonthDay(event, yearMonthDay);
        event.setEndDateFlagTrue();
        
        setDateYearMonthDay(event, yearMonthDay);
        event.setEndDateFlagFlase();
    }
 
   
    public String[] parseYearMonthDay (String timeInfo) 
    {
        String[] yearMonthDay = new String[SIZE_OF_TIME_ARRAY];
        
        yearMonthDay[YEAR_INDEX] = findMatch(FOUR_DIGIT_YEAR_REGEX.matcher(timeInfo));
        yearMonthDay[MONTH_INDEX] = findMatch(THREE_LETTER_MONTH_REGEX.matcher(timeInfo));
        yearMonthDay[DAY_INDEX] = findMatch(DATE_REGEX.matcher(timeInfo)).replaceAll(COMMA_CHARACTER, EMPTY_STRING);
    
        return yearMonthDay;
    }

}
