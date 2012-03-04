package parser;

import java.util.*;


import org.jdom.Element;
import org.jdom.filter.ElementFilter;

import event.Event;

import XMLNode.XMLNodeProgramme;
import XMLNodeFactory.XMLNodeFactory;

/**
 * Implements XML file parser functionality 
 * @author Andrew, Tianyu
 */
public class Parser {    
    
    private static final String XMLTV_ENTRY = "programme";
    private static final String START_ATTRIBUTE_VALUE = "start";
    private static final String STOP_ATTRIBUTE_VALUE = "stop";
    private static final String DASH_DELIMITER = " - ";
    private static final String COLON_DELIMITER = ": ";
    
    private static final int EMPTY_LIST = 0;
    private static final int OUT_OF_LIST_INDEX = -1;
    
    /**
     * 
     * @param filterTag
     * @param event
     * @param child
     * 
     * Special handler for XMLTV events.
     */
    public void handleXMLTV (String filterTag, Event event, Element child) {
        if (filterTag.equals(XMLTV_ENTRY)) { 
            XMLNodeProgramme.updateEvent(event, child.getAttributeValue(START_ATTRIBUTE_VALUE), child.getAttributeValue(STOP_ATTRIBUTE_VALUE));
        }
    }
    
    /**
     * @param parserFactory
     * @param eventList
     * 
     * Main function of Parser. Parses XML file by getting root element from ParserFactory and calling upon
     * specific XMLNodes to update newly created Events. Adds completed events to eventLists then returns
     * eventList once all events have been parsed.
     */
    public void parse(ParserFactory parserFactory, ArrayList<Event> eventList) {

        Element rootElement = parserFactory.getRootElement();
        XMLNodeFactory nodeFactory = parserFactory.getXMLNodeFactory();

        String filterTag = parserFactory.getXMLEntryName();
        ElementFilter filter = new ElementFilter(filterTag);

        List<Element> events = rootElement.getContent(filter);

        for (Element e : events) 
        {
            Event event = parserFactory.createEvent();
            handleXMLTV(filterTag, event, e);
            
            List<Element> children = e.getChildren();

            for (Element child : children) 
            {
                nodeFactory.setXMLNodeIndex(child.getName());
                String tagInfo = setTagInfo(child);
                
                if (nodeFactory.getXMLNodeIndex() > OUT_OF_LIST_INDEX)
                    nodeFactory.getXMLNodeType().updateEvent(event, tagInfo);
            }
            
            eventList.add(event);

        }

    }
    
    /**
     * 
     * @param child
     * @return
     * 
     * Gets string information from element. 
     */
    public String setTagInfo (Element child) 
    {
        String tagInfo;
        
        if (isParent(child))
            tagInfo = getChildrenText(child.getChildren());
        else
            tagInfo = child.getText();
        
        return tagInfo;
    }
    
    /**
     * 
     * @param children
     * @return
     * 
     * Concatenates children text into a single string that parent will use as its text.
     */
    public String getChildrenText(List<Element> children)
    {
        StringBuilder text = new StringBuilder();
        for (Element e: children)
        {
            text.append(DASH_DELIMITER + e.getName() + COLON_DELIMITER + e.getText());
        }
        return text.toString();
    }
    
    
    public boolean isParent(Element e)
    {
        List<Element> list = e.getChildren();
        return list.size() > EMPTY_LIST;
    }
    
}