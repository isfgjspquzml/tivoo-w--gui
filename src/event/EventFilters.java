
package event;

import java.util.ArrayList;
import java.util.Date;

import main.Main;

import exceptions.FilterException;

/**
 * Filters ArrayList<Event> based on specified criteria
 * @author James
 */
public class EventFilters implements EventConstants {
    
    private static final String NO_PARSED_EVENTS_TO_FILTER_MESSAGE = "No parsed events to filter!";
    
    public static ArrayList<Event> filterByTime(ArrayList<Event> parsedEvents,
            Date startDate, Date endDate) throws FilterException {
        if (parsedEvents.isEmpty()) {
            throw new FilterException(NO_PARSED_EVENTS_TO_FILTER_MESSAGE);
        }
        ArrayList<Event> filteredEvents = new ArrayList<Event>();
        for (Event e : parsedEvents) {
            Date startTime = e.getStartDate();
            Date endTime = e.getEndDate();
            if (startTime.after(startDate) && endTime.before(endDate)) {
                filteredEvents.add(e);
            }
        }
        return filteredEvents;
    }

    /**
     * Filters events by title
     * @param parsedEvents
     * @param titleFilter
     * @return
     * @throws FilterException
     */
    
    public static ArrayList<Event> filterByTitle(ArrayList<Event> parsedEvents,
            String titleFilter) throws FilterException {
        if (parsedEvents.isEmpty()) {
            throw new FilterException(NO_PARSED_EVENTS_TO_FILTER_MESSAGE);
        }
        ArrayList<Event> filteredEvents = new ArrayList<Event>();
        for (Event e : parsedEvents) {
            String title = e.getTitle();
            if (!(title.indexOf(titleFilter) >= 0)) {
                filteredEvents.add(e);
            }
        }
        return filteredEvents;
    }

    /**
     * Filters an ArrayList of Events based on an ArrayList of keyword strings.
     * If contains = true, The function returns an ArrayList of Events that DO
     * contain any of the given keywords in any attribute. If contains = false,
     * the function returns an ArrayList of Events that do NOT contain any of
     * the given keywords in any attribute
     * 
     * @param parsedEvents
     * @param keyWordFilter
     * @param contains
     * @return
     * @throws FilterException
     */

    public static ArrayList<Event> filterByKeyWord (
            ArrayList<Event> parsedEvents, ArrayList<String> keyWordFilter,
            boolean contains) throws FilterException 

    {

        if (parsedEvents.isEmpty()) {
            throw new FilterException(NO_PARSED_EVENTS_TO_FILTER_MESSAGE);
        }
        
        ArrayList<Event> filteredEvents = new ArrayList<Event>();
        
        for (Event e : parsedEvents) {
            ArrayList<String> stringAttributes = e.getAttributes();
            boolean containsKeyWord = false;
            for (String attribute : stringAttributes) {
                for (String keyWord : keyWordFilter) {
                    if (attribute.contains(keyWord)) {
                        containsKeyWord = true;
                    }
                }
            }
            if (contains) {
                if (containsKeyWord) {
                    filteredEvents.add(e);
                }
            } else {
                if (!containsKeyWord) {
                    filteredEvents.add(e);
                }
            }
        }
        
        return filteredEvents;
    }
    
    /**
     * Filters EventTVs by actor name
     * @param parsedTVEvents
     * @param actor
     * @return
     * @throws FilterException
     */
    
    public static ArrayList<Event> filterByActor(ArrayList<Event> eventList, String actor) throws FilterException 
    {
        removeEventTVs(eventList);
        
        ArrayList<EventTV> parsedTVEvents = Main.getEventTVList();
        
        if (parsedTVEvents.isEmpty()) {
            throw new FilterException(NO_PARSED_EVENTS_TO_FILTER_MESSAGE);
        }
        
        ArrayList<EventTV> filteredEvents = new ArrayList<EventTV>();        
        
        for (EventTV event : parsedTVEvents) {
            ArrayList<String> actors = event.getActors();
            if (!actors.isEmpty()) {
                for (String a : actors) {
                    if (a.contains(actor)) {
                        filteredEvents.add(event);
                    }
                }
            }
        }
        
        eventList.addAll(filteredEvents);
        
        return eventList;
    }
    
    public static void removeEventTVs (ArrayList<Event> eventList) 
    {            
        ArrayList<Event> dummyList = new ArrayList<Event>(eventList);
        for (Event event: dummyList) {
            if (event.getClass().getName().contains(EVENT_TV_CLASS_NAME)) eventList.remove(event);                
        }
    }
    
}