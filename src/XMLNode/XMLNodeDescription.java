package XMLNode;

import event.Event;

/**
 * XML Node object for "Description" tag
 * @author Andrew, Tianyu
 */
public class XMLNodeDescription extends XMLNode {

    public void updateEvent(Event event, String tagInfo) {
        event.setDescription(tagInfo);
    }
    
}
