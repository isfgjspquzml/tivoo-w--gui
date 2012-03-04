package XMLNodeFactory;

import XMLNode.XMLNode;
import XMLNode.XMLNodeAuthor;
import XMLNode.XMLNodeDescription;
import XMLNode.XMLNodeHourMinSecAMPM;
import XMLNode.XMLNodeMonthDayYearSlash;
import XMLNode.XMLNodeTitle;

/**
 * XMLNode Factory used to parse tags in ICS Calendar XML files
 * @author Andrew, Tianyu
 */
public class ICSCalXMLNodeFactory extends XMLNodeFactory {
    
    private static final String TITLE_TAG = "Subject";
    private static final String START_DATE_TAG = "StartDate";
    private static final String START_TIME_TAG = "StartTime";
    private static final String END_DATE_TAG = "EndDate";
    private static final String END_TIME_TAG = "EndTime";
    private static final String DESCRIPTION_TAG = "Description";
    private static final String AUTHOR_TAG = "Location";
    
    
    public ICSCalXMLNodeFactory () {
        this.setMyXMLNodeNames(myXMLNodeNames);
        this.setMyXMLNodes(myXMLNodes);
    }
    
    private String[] myXMLNodeNames = { 
                                      TITLE_TAG,
                                      START_DATE_TAG,
                                      START_TIME_TAG,
                                      END_DATE_TAG,
                                      END_TIME_TAG,
                                      DESCRIPTION_TAG,
                                      AUTHOR_TAG 
                                      };
            
    private XMLNode[] myXMLNodes = { 
                                     new XMLNodeTitle(),
                                     new XMLNodeMonthDayYearSlash(),
                                     new XMLNodeHourMinSecAMPM(),
                                     new XMLNodeMonthDayYearSlash(),
                                     new XMLNodeHourMinSecAMPM(),
                                     new XMLNodeDescription(),
                                     new XMLNodeAuthor() 
                                   };
      
}