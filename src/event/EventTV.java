package event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Specific event object used to represent TV calendar objects
 * @author Andrew, Tianyu
 */
public class EventTV extends Event {

    private static final String DIRECTOR_TAG = "director";
    private static final String PRESENTER_TAG = "presenter";
    private static final String PRODUCER_TAG = "producer";
    private static final String ACTOR_TAG = "actor";
    
    private static final String NULL_CREDITS = "NO_CREDITS";
    
    private static final String COLON_CHARACTER = ":";

    private static final String[] myCreditTags = {
                                                    DIRECTOR_TAG,
                                                    PRESENTER_TAG,
                                                    PRODUCER_TAG,
                                                    ACTOR_TAG
                                                 };
    
    private static final List<String> myCreditTagsList = Arrays.asList(myCreditTags);
    
    private static final Pattern CREDITS_REGEX =   
            Pattern.compile("[a-zA-Z]+(:){1}( ){1}[a-zA-Z]+( ){1}[a-zA-z]+");
    
    private static final int CREDITS_TAG_INDEX = 0;
    private static final int CREDITS_INFORMATION_INDEX = 1;
    
    private String myCredits;

    
    private ArrayList<String> myDirectors;
    private ArrayList<String> myPresenters;
    private ArrayList<String> myProducers;
    private ArrayList<String> myActors;

    private List<ArrayList<String>> mySpecificCreditsLists;
        
    
    public EventTV(Event event) {
        super(event);
        initializeEvent();
    }
    
    public EventTV() {
        super();
        initializeEvent();
    }
    
    public void initializeEvent () 
    {    
        myCredits = NULL_CREDITS;
        
        mySpecificCreditsLists = new ArrayList<ArrayList<String>>();
        
        myDirectors = new ArrayList<String>();
        mySpecificCreditsLists.add(myDirectors);
        
        myPresenters = new ArrayList<String>();
        mySpecificCreditsLists.add(myPresenters);

        myProducers = new ArrayList<String>();
        mySpecificCreditsLists.add(myProducers);
        
        myActors = new ArrayList<String>();
        mySpecificCreditsLists.add(myActors);
    }
        
    
    public void setCredits(String credits) {
        
        myCredits = credits;
                
        Matcher creditsMatcher = CREDITS_REGEX.matcher(credits);
        
        while (creditsMatcher.find()) {
            String credit = creditsMatcher.group();
            
            String[] creditArray = credit.split(COLON_CHARACTER);
            
            int index = myCreditTagsList.indexOf(creditArray[CREDITS_TAG_INDEX]);
            mySpecificCreditsLists.get(index).add(creditArray[CREDITS_INFORMATION_INDEX].trim());
        }
        
    }

    
    public String getCredits () {
        return myCredits;
    }
    
    public ArrayList<String> getDirectors () {
        return myDirectors;
    }    

    public ArrayList<String> getPresenters () {
        return myPresenters;
    }

    public ArrayList<String> getProducers () {
        return myProducers;
    }

    public ArrayList<String> getActors () {
        return myActors;
    }

    
}
