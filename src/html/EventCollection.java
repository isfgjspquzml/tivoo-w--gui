package html;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import event.Event;

/**
 * the abstract class of these collections
 * with some useful function in it
 * called HTMLHelpers before
 * 
 * @author Donghe
 */
public abstract class EventCollection implements HTMLConstants {

    public abstract void writeHTMLFiles() throws IOException;

    protected ArrayList<Event> myFilteredEvents; //this will be inherited by its children class
    protected ArrayList<Event> myBackupEvents; //this will be inherited by its children class
    
    public static ArrayList<File> topreview = new ArrayList<File>();

    public EventCollection(ArrayList<Event> filteredEvents,
            ArrayList<Event> backupEvents) {
        myFilteredEvents = filteredEvents;
        myBackupEvents = backupEvents;
    }

    /**
     * Add from Sunday to Saturday into the table head
     * 
     * @param table
     *            where the tag should be included
     */
    public void getTableHead(Tag table) {
        Tag tr = new Tag(HTML_TABLE_LINE);
        setTableHead(tr, HTML_DAY_SUN);
        setTableHead(tr, HTML_DAY_MON);
        setTableHead(tr, HTML_DAY_TUE);
        setTableHead(tr, HTML_DAY_WED);
        setTableHead(tr, HTML_DAY_THU);
        setTableHead(tr, HTML_DAY_FRI);
        setTableHead(tr, HTML_DAY_SAT);
        table.add(tr);
    }

    /**
     * write each tag of the day
     * 
     * @param tr
     *            where the tag should be included
     * @param day
     *            from Sunday to Saturday
     */
    public void setTableHead(Tag tr, String day) {
        Tag head = new Tag(HTML_TABLE_HEADER);
        head.add(day);
        tr.add(head);
    }

    /**
     * write html files
     * 
     * @param html
     *            the head of what you want to write
     * @param direction
     *            the path
     * @throws IOException
     */
    public void writeHTML(Tag html, String direction) throws IOException {
        File file = new File(direction);
        topreview.add(file);
        FileWriter fw = new FileWriter(file);
        fw.write(html.toString());
        fw.flush();
        fw.close();
    }

    /**
     * make the time formal, the output should be 05:00 pm intead of 5pm
     */
    @SuppressWarnings({ DEPRECATION })
    public String timeDisplay(Date date) {
        return getHourOrMinute(date.getHours()) + HTML_SPECIFIC_COLON
                + getHourOrMinute(date.getMinutes());
    }

    /**
     * get the hour or minute number in two-digit
     * 
     * @param HourOrMinute
     *            the hour or minute that need to be format
     * @return a two-digit number from 00 to 60
     */
    public String getHourOrMinute(int HourOrMinute) {
        if (HourOrMinute < INT_TEN)
            return STRING_ZERO + HourOrMinute;
        else
            return STRING_BLANK + HourOrMinute;
    }

    /**
     * write tag which includes its tag name and colon
     * 
     * @param tagName
     *            the tag name
     * @param tagContent
     *            the contend within the tag
     * @param body
     *            where the tag should be included
     */
    public void writeDiplayedTag(String tagName, String tagContent, Tag body) {
        Tag tag = new Tag(tagName);
        tag.add(tagName + HTML_SPECIFIC_COLON + tagContent + HTML_LINE_BREAK);
        body.add(tag);
    }

    /**
     * write a tag with attributes
     * 
     * @param tagName
     *            the tag name
     * @param tagContent
     *            the contend within the tag
     * @param body
     *            where the tag should be included
     * @param att
     *            the attributes
     */
    public void writeAttTag(String tagName, String tagContent, Tag body,
            String att) {
        Tag event = new Tag(HTML_LINK, att);
        event.add(tagContent);
        body.add(event);
    }

    /**
     * write a tag
     * 
     * @param tagName
     *            the tag name
     * @param tagContent
     *            the contend within the tag
     * @param body
     *            where the tag should be included
     */
    public void writeNormalTag(String tagName, String tagContent, Tag body) {
        Tag tag = new Tag(tagName);
        tag.add(tagContent);
        body.add(tag);
    }
}