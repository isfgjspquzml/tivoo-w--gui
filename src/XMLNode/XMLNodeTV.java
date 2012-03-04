package XMLNode;

import main.Main;
import event.Event;
import event.EventTV;

/**
 * XML Node object for "TV Credits" tag
 * Credits tag contains information on actors, directors, producers, and presenters of TV program
 * Creates EventTV object and updates the EventTV object because Events do not contain "credits" information
 * @author Andrew, Tianyu
 */
public class XMLNodeTV extends XMLNode {

    public void updateEvent(Event event, String tagInfo) {
        EventTV eventTV = new EventTV(event);
        eventTV.setCredits(tagInfo);
        Main.addEventTV(eventTV);
    }


}
