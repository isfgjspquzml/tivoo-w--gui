package html;

import java.io.IOException;
import java.util.ArrayList;

import event.Event;

/**
 * write the sorted event list
 * 
 * @author Donghe
 */
public class EventCollectionList extends EventCollection {

    public EventCollectionList(ArrayList<Event> filteredEvents,
            ArrayList<Event> backupEvents) {
        super(filteredEvents, backupEvents);
    }

    /**
     * write the sorted event list
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

        for (Event e : myFilteredEvents) {
            writeEvent(e, myFilteredEvents.indexOf(e), body);
        }
        html.add(body);

        writeHTML(html, HTML.getHTMLFolderPath() + HTML_SPECIFIC_SLASH
                + HTML.getCalName() + HTML_SPECIFIC_SUMMARY_LIST + HTML_END);
    }

    /**
     * write each event
     * 
     * @param e
     *            the event
     * @param index
     *            the index in the list
     * @param body
     *            where the tag should be included
     */
    public void writeEvent(Event e, int index, Tag body) {
        String att = HTML_LINK_ATTRIBUTE + HTML.getHTMLFolderPath()
                + HTML_SPECIFIC_SLASH + HTML.getCalName() + index + HTML_END;
        writeAttTag(HTML_LINK, e.getTitle(), body, att);
        writeNormalTag(HTML_TEXT_ITALIC, e.getStartDate().toString(), body);
        body.add(HTML_SPECIFIC_TO);
        writeNormalTag(HTML_TEXT_ITALIC, e.getEndDate().toString(), body);
        body.add(HTML_LINE_BREAK);
    }
}