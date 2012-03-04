package XMLNodeFactory;

import XMLNode.XMLNode;
import XMLNode.XMLNodeAuthor;
import XMLNode.XMLNodeDescription;
import XMLNode.XMLNodeGoogleSummary;
import XMLNode.XMLNodeTitle;

/**
 * XMLNode Factory used to parse tags in Google Calendar XML files
 * @author Andrew, Tianyu
 */
public class GoogleCalXMLNodeFactory extends XMLNodeFactory {
    
    private static final String DESCRIPTION_TAG = "id";
    private static final String TITLE_TAG = "title";
    private static final String DATE_TAG = "summary";
    private static final String AUTHOR_TAG = "author";
    
    
    public GoogleCalXMLNodeFactory () {
        this.setMyXMLNodeNames(myXMLNodeNames);
        this.setMyXMLNodes(myXMLNodes);
    }
    
    private String[] myXMLNodeNames = { 
                                      DESCRIPTION_TAG,
                                      TITLE_TAG,
                                      DATE_TAG,
                                      AUTHOR_TAG 
                                      };
            
    private XMLNode[] myXMLNodes = { 
                                     new XMLNodeDescription(),
                                     new XMLNodeTitle(),
                                     new XMLNodeGoogleSummary(),
                                     new XMLNodeAuthor() 
                                   };

}
