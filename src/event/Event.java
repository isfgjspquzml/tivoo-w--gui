
package event;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Event object used to represent parsed events
 * @author Donghe, James, Andrew, Tianyu
 */

public class Event implements Comparable<Event>, EventConstants {
    
    private Map<Integer, String> myAttributes;
    
    private Date myStartDate;
    private Date myEndDate;
    private boolean myEndDateFlag;            
    private Date RIGHT_NOW;
    
        
    public Event(Event e) {
        myAttributes = new HashMap<Integer, String>();
        myAttributes.put(TITLE_INDEX, e.getTitle());
        myAttributes.put(DESCRIPTION_INDEX, e.getDescription());
        myAttributes.put(AUTHOR_INDEX, e.getAuthor());
        myAttributes.put(START_DAY_INDEX, e.getStartDay());
        myAttributes.put(END_DAY_INDEX, e.getEndDay());
        this.myStartDate = e.myStartDate;
        this.myEndDate = e.myEndDate;
        this.RIGHT_NOW = e.RIGHT_NOW;
        this.myEndDateFlag = e.myEndDateFlag;
        this.myAttributes = e.myAttributes;
    }

    public Event() {
        myAttributes = new HashMap<Integer, String>();
        myAttributes.put(TITLE_INDEX, NULL_TITLE);
        myAttributes.put(DESCRIPTION_INDEX, NULL_DESCRIPTION);
        myAttributes.put(AUTHOR_INDEX, NULL_AUTHOR);
        myAttributes.put(START_DAY_INDEX, NULL_START_DAY);
        myAttributes.put(END_DAY_INDEX, NULL_END_DAY);
        myStartDate = new Date();
        myEndDate = new Date();
        RIGHT_NOW = new Date(myStartDate.getTime());
        myEndDateFlag = false;
    }
   
    public boolean isStartDateDefault () {
        return (myStartDate.getYear() == RIGHT_NOW.getYear() &&
                myStartDate.getMonth() == RIGHT_NOW.getMonth() &&
                myStartDate.getDate() == RIGHT_NOW.getDate());
    }
    
    public boolean isStartTimeDefault () {
        return (myStartDate.getHours() == RIGHT_NOW.getHours() &&
                myStartDate.getMinutes() == RIGHT_NOW.getMinutes() 
                );
    }
    
    
    public ArrayList<String> getAttributes(){
        return (ArrayList<String>) myAttributes.values();
    }
    
    public String getTitle() {
        return myAttributes.get(TITLE_INDEX);
    }
    
    public String getDescription(){
        return myAttributes.get(DESCRIPTION_INDEX);
    }

    public String getAuthor(){
        return myAttributes.get(AUTHOR_INDEX);
    }
    
    public String getStartDay() {
        return myAttributes.get(START_DAY_INDEX);
    }

    public String getEndDay() {
        return myAttributes.get(END_DAY_INDEX);
    }
   
    public Date getDate() {
        return myEndDateFlag ? myEndDate : myStartDate;
    }

    public Date getStartDate() {
        return myStartDate;
    }

    public Date getEndDate() {
        return myEndDate;
    }
 
    public void setTitle(String title) {
        String originalTitle = myAttributes.get(TITLE_INDEX);
        if (originalTitle.equals(NULL_TITLE)) myAttributes.put(TITLE_INDEX, title);
        else myAttributes.put(TITLE_INDEX, concatenateInformation(originalTitle, title));
    }
    
    public void setDescription(String description) {
        String originalDescription = myAttributes.get(DESCRIPTION_INDEX);
        if (originalDescription.equals(NULL_DESCRIPTION)) myAttributes.put(DESCRIPTION_INDEX, description);
        else myAttributes.put(DESCRIPTION_INDEX, concatenateInformation(originalDescription, description));
    }

    public void setAuthor(String author) {
        String originalAuthor = myAttributes.get(AUTHOR_INDEX);
        if (originalAuthor.equals(NULL_AUTHOR)) myAttributes.put(AUTHOR_INDEX, author);
        else myAttributes.put(AUTHOR_INDEX, concatenateInformation(originalAuthor, author));
    }
  
    public void setStartDay(String startDay) {
        if (myAttributes.get(START_DAY_INDEX).equals(NULL_START_DAY)) myAttributes.put(START_DAY_INDEX, startDay);
    }

    public void setEndDay(String endDay) {
        if (myAttributes.get(END_DAY_INDEX).equals(NULL_END_DAY)) myAttributes.put(END_DAY_INDEX, endDay);
    }

    
    public void setDay(int dayIndex) {
        if (myEndDateFlag) myAttributes.put(END_DAY_INDEX, myDays[dayIndex]);
        else myAttributes.put(START_DAY_INDEX, myDays[dayIndex]);
    }
    
    public void setDate(Date date) {
        if (myEndDateFlag) myEndDate = date;
        else myStartDate = date;
    }
    
    public boolean getEndDateFlag () {
        return myEndDateFlag;
    }
    
    public void setEndDateFlagTrue () {
        myEndDateFlag = true;
    }

    public void setEndDateFlagFlase () {
        myEndDateFlag = false;
    }

    public String concatenateInformation (String original, String addition) {
        return original + " - " + addition;
    }

    public int compareTo(Event other) {
        if (this.myStartDate.before(other.myStartDate))
            return THIS_DATE_BEFORE;
        else if (this.myStartDate.after(other.myStartDate))
            return THIS_DATE_AFTER;
        else if ((this.myEndDate).before(other.myEndDate))
            return THIS_DATE_BEFORE;
        else if ((this.myEndDate).after(other.myEndDate))
            return THIS_DATE_AFTER;
        else
            return THIS_DATE_EQUAL;
    }
    
    public void print() {

        System.out
                .printf("Title: %s \nDescription: %s \nStart time: %s \nEnd time: %s \nStart day: %s \nEnd day: %s \nAuthor: %s\n",
                        myAttributes.get(TITLE_INDEX), myAttributes.get(DESCRIPTION_INDEX), myStartDate.toString(),
                        myEndDate.toString(), myAttributes.get(START_DAY_INDEX), myAttributes.get(END_DAY_INDEX), myAttributes.get(AUTHOR_INDEX));
    }
    
}