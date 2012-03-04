package html;

import java.io.IOException;
import java.util.ArrayList;

import event.Event;

/**
 * write the event list which make the conflict events in red
 * 
 * @author Donghe
 */
public class EventCollectionListConflict extends EventCollection {

    public EventCollectionListConflict(ArrayList<Event> filteredEvents,
            ArrayList<Event> backupEvents) {
        super(filteredEvents, backupEvents);
    }

    /**
     * write the conflict event list
     * 
     * @param myFilteredEvents
     *            the event list
     * @throws IOException
     */
    public void writeHTMLFiles() throws IOException {
        Tag html = new Tag(HTML_HEAD);
        Tag body = new Tag(HTML_BODY);
        writeDiplayedTag(HTML_TITLE, HTML.getCalName() + HTML_SPECIFIC_SUMMARY_LIST,
                body);
        for (int i = 0; i < myFilteredEvents.size(); i++) {
            writeEvent(i, body);
        }
        html.add(body);

        writeHTML(html, HTML.getHTMLFolderPath() + HTML_SPECIFIC_SLASH
                + HTML.getCalName() + HTML_SPECIFIC_SUMMARY_LIST_CONFLICT + HTML_END);
    }

    /**
     * write this event
     * 
     * @param myFilteredEvents
     *            the event list
     * @param index
     *            the index in this list
     * @param body
     *            where the tag should be included
     */
    public void writeEvent(int index, Tag body) {
        Event e = myFilteredEvents.get(index);
        String att = HTML_LINK_ATTRIBUTE + HTML.getHTMLFolderPath()
                + HTML_SPECIFIC_SLASH + HTML.getCalName()
                + myBackupEvents.indexOf(e) + HTML_END;
        writeAttTag(HTML_LINK, e.getTitle(), body, att);
        writeStartTime(index, e.getStartDate().toString(), body);
        body.add(HTML_SPECIFIC_TO);
        writeEndTime(index, e.getEndDate().toString(), body);
        body.add(HTML_LINE_BREAK);
    }

    /**
     * write the start time
     * 
     * @param myFilteredEvents
     *            the event list
     * @param index
     *            the index in this list
     * @param date
     *            the date of the event
     * @param body
     *            where the tag should be included
     */
    private void writeStartTime(int index, String date, Tag body) {
        if (startTimeConflict(index)) {
            writeConflictTime(date, body);
        } else {
            writeNonConflicTime(date, body);
        }
    }

    /**
     * write the end time
     * 
     * @param myFilteredEvents
     *            the event list
     * @param index
     *            the index in this list
     * @param date
     *            the date of the event
     * @param body
     *            where the tag should be included
     */
    private void writeEndTime(int index, String date, Tag body) {
        if (endTimeConflict(index)) {
            writeConflictTime(date, body);
        } else {
            writeNonConflicTime(date, body);
        }
    }

    /**
     * write the conflict time in red
     * 
     * @param date
     *            the date
     * @param body
     *            where the tag should be included
     */
    private void writeConflictTime(String date, Tag body) {
        Tag font = new Tag(HTML_FONT, HTML_COLOR_RED);
        writeNormalTag(HTML_TEXT_ITALIC, date, font);
        body.add(font);
    }

    /**
     * write the NON-conflict time in black
     * 
     * @param date
     *            the date
     * @param body
     *            where the tag should be included
     */
    private void writeNonConflicTime(String date, Tag body) {
        writeNormalTag(HTML_TEXT_ITALIC, date, body);
    }

    /**
     * @param myFilteredEvents
     *            the event list
     * @param index
     *            the event index in the list
     * @return check whether the start time is conflict with the end time of
     *         last event
     */
    private boolean startTimeConflict(int index) {
        if (notTheFirstEvent(index)) {
            Event lastEvent = myFilteredEvents.get(index - 1);
            Event currentEvent = myFilteredEvents.get(index);
            if (lastEvent.getEndDate().after(currentEvent.getStartDate()))
                return true;
        }
        return false;
    }

    /**
     * @param myFilteredEvents
     *            the event list
     * @param index
     *            the event index in the list
     * @return check whether the end time is conflict with the start time of
     *         next event
     */
    private boolean endTimeConflict(int index) {
        if (notTheLastEvent(index)) {
            Event nextEvent = myFilteredEvents.get(index + 1);
            Event currentEvent = myFilteredEvents.get(index);
            if (nextEvent.getStartDate().before(currentEvent.getEndDate()))
                return true;
        }
        return false;
    }

    /**
     * @param myFilteredEvents
     *            the event list
     * @param index
     *            the event index in the list
     * @return it is not the first event in the list
     */
    private boolean notTheFirstEvent(int index) {
        return index > 0;
    }

    /**
     * @param myFilteredEvents
     *            the event list
     * @param index
     *            the event index in the list
     * @return it is not the last event in the list
     */
    private boolean notTheLastEvent(int index) {
        return index < myFilteredEvents.size() - 1;
    }
}