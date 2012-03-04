package XMLNodeFactory;

import XMLNode.XMLNode;
import XMLNode.XMLNodeAuthor;
import XMLNode.XMLNodeDescription;
import XMLNode.XMLNodeDate;
import XMLNode.XMLNodeTitle;

/**
 * XMLNode Factory used to parse tags in CSV Calendar XML files
 * @author Andrew, Tianyu
 */
public class CSVCalXMLNodeFactory extends XMLNodeFactory {

    private static final String TITLE_TAG = "Col1";
    private static final String AUTHOR_TAG = "Col2";
    private static final String START_DATE_TAG = "Col8";
    private static final String END_DATE_TAG = "Col9";
    private static final String DESCRIPTION_TAG = "Col15";        
    
    
    public CSVCalXMLNodeFactory() {
        this.setMyXMLNodeNames(myXMLNodeNames);
        this.setMyXMLNodes(myXMLNodes);
    }

    private String[] myXMLNodeNames = { 
                                        TITLE_TAG, 
                                        AUTHOR_TAG,
                                        START_DATE_TAG, 
                                        END_DATE_TAG, 
                                        DESCRIPTION_TAG 
                                      }; 

    private XMLNode[] myXMLNodes = { 
                                        new XMLNodeTitle(),
                                        new XMLNodeAuthor(), 
                                        new XMLNodeDate(),
                                        new XMLNodeDate(), 
                                        new XMLNodeDescription() };

}
