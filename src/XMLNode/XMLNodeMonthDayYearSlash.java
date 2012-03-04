package XMLNode;

import event.Event;

/**
 * XML Node object for "MMDDYYYYDate" tag
 * Date tag contains year, month, day information in MM/DD/YYYY format
 * Reformats its date information to YYYY/MM/DD format
 * @author Andrew, Tianyu
 */
public class XMLNodeMonthDayYearSlash extends XMLNode 
{
    private static int MMDDYYYY_MONTH_INDEX = 0;
    private static int MMDDYYYY_DAY_INDEX = 1;
    private static int MMDDYYYY_YEAR_INDEX = 2;
    
    
    public void updateEvent(Event event, String tagInfo) 
    {        
        if (!event.isStartDateDefault()) event.setEndDateFlagTrue();
        
        setDateYearMonthDay(event, reformatYearMonthDay(tagInfo.split(getSeparatorCharacter(tagInfo))));
    
        if (event.getEndDateFlag()) event.setEndDateFlagFlase();

    }
    
    public String[] reformatYearMonthDay (String[] timeInfo) 
    {
        String[] reformattedYearMonthDay = new String[SIZE_OF_TIME_ARRAY];
        
        reformattedYearMonthDay[YEAR_INDEX] = timeInfo[MMDDYYYY_YEAR_INDEX];
        reformattedYearMonthDay[MONTH_INDEX] = timeInfo[MMDDYYYY_MONTH_INDEX];
        reformattedYearMonthDay[DAY_INDEX] = timeInfo[MMDDYYYY_DAY_INDEX];

        return reformattedYearMonthDay;
    }

}
