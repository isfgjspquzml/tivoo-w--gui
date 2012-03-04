package XMLNode;

/**
 * XML Node object used to represent tags in XML files
 * @author Andrew, Tianyu
 */
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import event.Event;
import event.EventConstants;


public abstract class XMLNode implements XMLNodeConstants, EventConstants {

    
    public abstract void updateEvent(Event event, String tagInfo);

    
    public Matcher getYearMonDayMatcher (String tagInfo) {
        return getMatcher(myYearMonthDayPatterns, tagInfo);
    }
    
    public Matcher getHourMinSecMatcher (String tagInfo) {
        return getMatcher(myHourMinSecPatterns, tagInfo);
    }
   
    private Matcher getMatcher (Pattern[] patterns, String tagInfo) {
        Matcher matcher = null;
        for (Pattern pattern: patterns) {
            if (pattern.matcher(tagInfo).find()) matcher = pattern.matcher(tagInfo);
        }
        return matcher;
    }
    
    
    public String getSeparatorCharacter (String tagInfo) {
        return tagInfo.contains(SLASH_CHARACTER) ? SLASH_CHARACTER : DASH_CHARACTER;
    }
    
    public void setDateInformation(Matcher matcher, Event event, String monthDayYearString) {
        int index = myYearMonDayPatternsList.indexOf(matcher.pattern());
        myYearMonDayNodeFormats[index].updateEvent(event, monthDayYearString);
    }
    
    public void setTimeInformation(Matcher matcher, Event event, String hourMinSecString) {
        int index = myHourMinSecPatternsList.indexOf(matcher.pattern());
        myHourMinSecNodeFormats[index].updateEvent(event, hourMinSecString);
    }
    
    
    public static int formatYear (String year)
    {
        int yearInt = Integer.parseInt(year);
        return yearInt = year.length() == TWO_DIGIT_YEAR_FORMAT ? yearInt + DATE_TWO_DIGIT_YEAR_OFFSET : yearInt + DATE_FOUR_DIGIT_YEAR_OFFSET;
    }

    public static int formatMonth (String month)
    {
        if (myMonthsList.contains(month)) return myMonthsList.indexOf(month);
        return Integer.parseInt(month) + DATE_MONTH_OFFSET;
    }
    
    public String findMatch(Matcher matcher)
    {
        String targetString = new String();
        while(matcher.find()) targetString = matcher.group();
        return targetString;
    }
    

    public static void setDateHourMinuteSecond (Date date, int[] hourAndMinute)
    {        
        date.setHours(hourAndMinute[HOUR_INDEX]);
        date.setMinutes(hourAndMinute[MINUTE_INDEX]);
        date.setSeconds(hourAndMinute[SECONDS_INDEX]);
    }

    
    public static void setDateYearMonthDay (Event event, String[] yearMonthDay)
    {
        Date date = event.getDate();

        date.setYear(formatYear(yearMonthDay[YEAR_INDEX]));
        date.setMonth(formatMonth(yearMonthDay[MONTH_INDEX]));
        date.setDate(Integer.parseInt(yearMonthDay[DAY_INDEX]));

        event.setDay(date.getDay());
    }

    
}
