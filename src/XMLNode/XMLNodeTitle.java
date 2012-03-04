package XMLNode;

import event.Event;

/**
 * XML Node object for "Title" tag
 * @author Andrew, Tianyu
 */
public class XMLNodeTitle extends XMLNode {

    public void updateEvent(Event event, String tagInfo) {
        event.setTitle(tagInfo);
    }

}
