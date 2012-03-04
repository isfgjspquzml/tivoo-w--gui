package XMLNode;

import event.Event;

/**
 * XML Node object for "TV Programme" tag
 * Programme tag contains year, month, day information in YYYYMMDD String format
 * @author Andrew, Tianyu
 */
public class XMLNodeProgramme extends XMLNode {

    private static final int YEAR_BEGIN_INDEX = 0;
    private static final int YEAR_END_INDEX = 4;
    
    private static final int MONTH_BEGIN_INDEX = 4;
    private static final int MONTH_END_INDEX = 6;
    
    private static final int DAY_BEGIN_INDEX = 6;
    private static final int DAY_END_INDEX = 8;
    
    
    private static final int HOUR_BEGIN_INDEX = 8;
    private static final int HOUR_END_INDEX = 10;
    
    private static final int MINUTE_BEGIN_INDEX = 10;
    private static final int MINUTE_END_INDEX = 12;
    
    private static final int SECONDS_BEGIN_INDEX = 12;
    private static final int SECONDS_END_INDEX = 14;
    

    
    public static void updateEvent(Event event, String startTime, String endTime) 
    { 
        String[] startYearMonthDay = parseYearMonthDay(startTime);
        int[] startHourMinuteSecond = parseHourMinuteSecond(startTime);
        
        setDateYearMonthDay(event, startYearMonthDay);
        setDateHourMinuteSecond(event.getStartDate(), startHourMinuteSecond);
        event.setEndDateFlagTrue();
        
        
        String[] endYearMonthDay = parseYearMonthDay(endTime);
        int[] endHourMinuteSecond = parseHourMinuteSecond(endTime);
        
        setDateYearMonthDay(event, endYearMonthDay);
        setDateHourMinuteSecond(event.getEndDate(), endHourMinuteSecond);
        event.setEndDateFlagFlase();
    }

    
    
    public void updateEvent(Event event, String tagInfo) { }

    
    
    public static String[] parseYearMonthDay (String timeInfo) 
    {
        String[] yearMonthDay = new String[SIZE_OF_TIME_ARRAY];
    
        yearMonthDay[YEAR_INDEX] = timeInfo.substring(YEAR_BEGIN_INDEX, YEAR_END_INDEX);
        yearMonthDay[MONTH_INDEX] = timeInfo.substring(MONTH_BEGIN_INDEX, MONTH_END_INDEX);
        yearMonthDay[DAY_INDEX] = timeInfo.substring(DAY_BEGIN_INDEX, DAY_END_INDEX);        
        
        return yearMonthDay;
    }
    
    public static int[] parseHourMinuteSecond (String timeInfo) 
    {
        int[] hourMinuteSecond = new int[SIZE_OF_TIME_ARRAY];
    
        hourMinuteSecond[HOUR_INDEX] = Integer.parseInt(timeInfo.substring(HOUR_BEGIN_INDEX, HOUR_END_INDEX));
        hourMinuteSecond[MINUTE_INDEX] = Integer.parseInt(timeInfo.substring(MINUTE_BEGIN_INDEX, MINUTE_END_INDEX));
        hourMinuteSecond[SECONDS_INDEX] = Integer.parseInt(timeInfo.substring(SECONDS_BEGIN_INDEX, SECONDS_END_INDEX));        
        
        return hourMinuteSecond;
    }
    
    
}
