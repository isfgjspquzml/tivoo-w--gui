package XMLNode;

import event.Event;

/**
 * XML Node object for "MilitaryTime" tag
 * Time contains hour, minutes, seconds information in military time format
 * @author Andrew, Tianyu
 */
public class XMLNodeHour24MinSecond extends XMLNode {

    public void updateEvent(Event event, String tagInfo) 
    {
        int[] timeInfo = parse24HourMinuteSecondTimeFormat(tagInfo);
        
        if (!event.isStartTimeDefault()) setDateHourMinuteSecond (event.getEndDate(), timeInfo);
        else setDateHourMinuteSecond (event.getStartDate(), timeInfo);

    }
    
    public int[] parse24HourMinuteSecondTimeFormat (String hourMinuteSecond) {
        
        String[] hourMinuteSecondArray = hourMinuteSecond.split(COLON_CHARACTER);
        int[] timeInfo = new int[SIZE_OF_TIME_ARRAY];
       
        timeInfo[HOUR_INDEX] = Integer.parseInt(hourMinuteSecondArray[HOUR_INDEX]);
        timeInfo[MINUTE_INDEX] = Integer.parseInt(hourMinuteSecondArray[MINUTE_INDEX]);
        timeInfo[SECONDS_INDEX] = DEFAULT_TIME;
        
        return timeInfo;
    }

}

