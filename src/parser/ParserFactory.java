package parser;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import event.Event;
import event.EventTV;

import XMLNodeFactory.CSVCalXMLNodeFactory;
import XMLNodeFactory.DukeCalXMLNodeFactory;
import XMLNodeFactory.GoogleCalXMLNodeFactory;
import XMLNodeFactory.ICSCalXMLNodeFactory;
import XMLNodeFactory.TVCalXMLNodeFactory;
import XMLNodeFactory.XMLNodeFactory;

/**
 * Creates XMLNodeFactory based on type of file being parsed
 * @author Andrew, Tianyu
 */
public class ParserFactory {
        
    private static final String DUKE_CAL_ID = "events";
    private static final String ICS_CAL_ID = "dataroot";
    private static final String GOOGLE_CAL_ID = "feed";
    private static final String CSV_CAL_ID = "document";
    private static final String TV_CAL_ID = "tv";

    private static final String DUKE_CAL_ENTRY = "event";
    private static final String ICS_CAL_ENTRY = "Calendar";
    private static final String GOOGLE_CAL_ENTRY = "entry";
    private static final String CSV_CAL_ENTRY = "row";
    private static final String TV_CAL_ENTRY = "programme";

    private static final String[] myParserIdentifiers = { 
        DUKE_CAL_ID,
        ICS_CAL_ID,
        GOOGLE_CAL_ID,
        CSV_CAL_ID,
        TV_CAL_ID
     };

    private static final List<String> myParserIdentifiersList = Arrays.asList(myParserIdentifiers);



    private static final String[] myXMLEntryNames = { 
                                                        DUKE_CAL_ENTRY,
                                                        ICS_CAL_ENTRY,
                                                        GOOGLE_CAL_ENTRY,
                                                        CSV_CAL_ENTRY,
                                                        TV_CAL_ENTRY
                                                    };

    private File myXMLFile;
    private static int myXMLIndex;

    private XMLNodeFactory[] myXMLNodeFactories = {
                                                    new DukeCalXMLNodeFactory(),
                                                    new ICSCalXMLNodeFactory(),
                                                    new GoogleCalXMLNodeFactory(),
                                                    new CSVCalXMLNodeFactory(),
                                                    new TVCalXMLNodeFactory() 
                                                  };
 
    public ParserFactory(File XMLFile) 
    {
        myXMLFile = XMLFile;
    }    
    
    public XMLNodeFactory getXMLNodeFactory () {
        return myXMLNodeFactories[myXMLIndex];
    }
    
    public String getXMLEntryName () {
        return myXMLEntryNames[myXMLIndex];
    }
    
    public Event createEvent () {
        if (myXMLIndex == myParserIdentifiersList.indexOf(TV_CAL_ID)) return new EventTV();
        return new Event();
    }
    
    
    public Element getRootElement () {

        Element rootElement = null;
        SAXBuilder builder = new SAXBuilder();
        
        try {

            Document document = (Document) builder.build(myXMLFile);
            rootElement = document.getRootElement();
        
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        
        return rootElement;
        
    }
    
    public void setXMLIndex () {
        myXMLIndex = myParserIdentifiersList.indexOf(getRootElement().getName()); 
    }

    public int getXMLIndex () {
        return myXMLIndex;
    }
    
}
