package html;

import java.io.IOException;
import java.util.ArrayList;

import event.Event;

/**
 * generate events one by one in detail
 * 
 * @author Donghe
 */
public class EventCollectionSeparate extends EventCollection {

    public EventCollectionSeparate(ArrayList<Event> filteredEvents,
            ArrayList<Event> backupEvents) {
        super(filteredEvents, backupEvents);
    }

    /**
     * generate events in this list
     * 
     * @param myFilteredEvents
     *            the event list
     */
    public void writeHTMLFiles() throws IOException {
        for (Event e : myFilteredEvents) {
            writeEachEvent(e, myFilteredEvents.indexOf(e));
        }
    }

    /**
     * generate each specific event
     * 
     * @param event
     *            the event you want to write
     * @param index
     *            the index of this list
     * @throws IOException
     */
    public void writeEachEvent(Event event, int index) throws IOException {
        Tag html = new Tag(HTML_HEAD);
        Tag body = new Tag(HTML_BODY);

        writeDiplayedTag(HTML_TITLE, event.getTitle(), body);
        writeDiplayedTag(HTML_SPECIFIC_DESCRIPTION, event.getDescription(),
                body);
        writeDiplayedTag(HTML_SPECIFIC_TIME_START, event.getStartDate()
                .toString(), body);
        writeDiplayedTag(HTML_SPECIFIC_TIME_END, event.getEndDate().toString(),
                body);
        writeDiplayedTag(HTML_SPECIFIC_DAY, event.getStartDay(), body);
        writeDiplayedTag(HTML_SPECIFIC_AUTHOR, event.getAuthor(), body);
        html.add(body);

        writeHTML(html, HTML.getHTMLFolderPath() + HTML_SPECIFIC_SLASH
                + HTML.getCalName() + index + HTML_END);
    }

}