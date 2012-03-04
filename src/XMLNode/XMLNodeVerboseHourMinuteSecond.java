package XMLNode;

import java.util.regex.Matcher;

import event.Event;

/**
 * XML Node object for "Time" tag
 * Used in parsing GoogleCal summary information
 * Summary represents hour, minute, second information as a String (i.e. 5:30pm to 7pm) 
 * @author Andrew, Tianyu
 */
public class XMLNodeVerboseHourMinuteSecond extends XMLNode {

    private static final int START_TIME_INDEX = 0;
    private static final int END_TIME_INDEX = 1;
    private static final int SIZE_START_END_TIME_ARRAY = 2;
    
    private boolean myFlagNoHourAndMinute;

    
    public void updateEvent(Event event, String tagInfo)
    {
        myFlagNoHourAndMinute = false;
        
        int[] startHourAndMinuteInt = DEFAULT_START_HOUR_MIN_SECOND;
        int[] endHourAndMinuteInt = DEFAULT_END_HOUR_MIN_SECOND;
        

        String[] startEndHourMinute = parseAMPMTimeFormatString(tagInfo);
        
        if (!myFlagNoHourAndMinute)
        {
            startHourAndMinuteInt = XMLNodeHourMinSecAMPM.parseGeneralAMPMTimeFormat(startEndHourMinute[START_TIME_INDEX]);
            endHourAndMinuteInt = XMLNodeHourMinSecAMPM.parseGeneralAMPMTimeFormat(startEndHourMinute[END_TIME_INDEX]);  
        }

        else myFlagNoHourAndMinute = false;
        
        setDateHourMinuteSecond(event.getStartDate(), startHourAndMinuteInt);
        setDateHourMinuteSecond(event.getEndDate(), endHourAndMinuteInt);
    }

    public String[] parseAMPMTimeFormatString (String timeInfo) 
    {    
        String[] startEndTimeInfo = new String[SIZE_START_END_TIME_ARRAY];
        int index = START_TIME_INDEX;
        
        Matcher matcher = AMPM_TIME_REGEX.matcher(timeInfo);
        
        while (matcher.find() && index < SIZE_START_END_TIME_ARRAY) {
            startEndTimeInfo[index] = matcher.group();
            ++index;
        }
        
        if (index == START_TIME_INDEX) myFlagNoHourAndMinute = true; 
        return startEndTimeInfo;
    }
    
}
