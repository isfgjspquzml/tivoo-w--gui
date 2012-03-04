package XMLNode;

import event.Event;

/**
 * XML Node object for "Time" tag
 * Date tag contains year, month, day information in YYYY/MM/DD format
 * This has been set as the general date format to be used by XML Node
 * @author Andrew, Tianyu
 */
public class XMLNodeYearMonthDayDash extends XMLNode 
{
    
    public void updateEvent(Event event, String tagInfo) 
    {   
        if (!event.isStartDateDefault()) event.setEndDateFlagTrue();
        
        setDateYearMonthDay(event, tagInfo.split(getSeparatorCharacter(tagInfo)));
        
        if (event.getEndDateFlag()) event.setEndDateFlagFlase();
    }

}
