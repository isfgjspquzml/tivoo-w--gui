package XMLNode;

import event.Event;

/**
 * XML Node object for "Author" tag
 * @author Andrew, Tianyu
 */
public class XMLNodeAuthor extends XMLNode {
    
    public void updateEvent(Event event, String tagInfo) {
        event.setAuthor(tagInfo);
    }
    
}
