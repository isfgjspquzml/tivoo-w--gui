package XMLNode;

import event.Event;

/**
 * XML Node object for "Summary" tag in GoogleCal
 * Summary tag contains all time information (year, month, day, hours, minutes, seconds)
 * @author Andrew, Tianyu
 */
public class XMLNodeGoogleSummary extends XMLNode {

    private static final String RECURRING_TAG = "Recurring";

    
    public void updateEvent(Event event, String tagInfo) 
    {
        if (tagInfo.contains(RECURRING_TAG)) 
        {
            XMLNodeDate node = new XMLNodeDate();
            node.updateEvent(event, tagInfo);
        }
        
        else 
        {
            XMLNode yearMonthDayNode = new XMLNodeVerboseYearMonthDay();
            XMLNode hourMinuteSecondNode = new XMLNodeVerboseHourMinuteSecond();
            
            yearMonthDayNode.updateEvent(event, tagInfo);
            hourMinuteSecondNode.updateEvent(event, tagInfo);
        }
    }      
   
}
