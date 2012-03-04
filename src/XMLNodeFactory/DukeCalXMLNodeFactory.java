package XMLNodeFactory;

import XMLNode.XMLNode;
import XMLNode.XMLNodeAuthor;
import XMLNode.XMLNodeDescription;
import XMLNode.XMLNodeDate;
import XMLNode.XMLNodeTitle;

/**
 * XMLNode Factory used to parse tags in Duke Calendar XML files
 * @author Andrew, Tianyu
 */
public class DukeCalXMLNodeFactory extends XMLNodeFactory{

    private static final String START_DATE_TAG = "start";
    private static final String END_DATE_TAG = "end";
    private static final String DESCRIPTION_TAG = "description";
    private static final String TITLE_TAG = "summary";
    private static final String AUTHOR_TAG = "creator";
    
    
    public DukeCalXMLNodeFactory () {
        this.setMyXMLNodeNames(myXMLNodeNames);
        this.setMyXMLNodes(myXMLNodes);
    }
    
    private String[] myXMLNodeNames = { 
                                      START_DATE_TAG,
                                      END_DATE_TAG,
                                      DESCRIPTION_TAG, 
                                      TITLE_TAG, 
                                      AUTHOR_TAG
                                      };
    
        
    private XMLNode[] myXMLNodes = { 
                                     new XMLNodeDate(),
                                     new XMLNodeDate(),
                                     new XMLNodeDescription(),
                                     new XMLNodeTitle(),
                                     new XMLNodeAuthor()
                                   };
    
}
