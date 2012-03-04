package XMLNodeFactory;

import XMLNode.XMLNode;
import XMLNode.XMLNodeAuthor;
import XMLNode.XMLNodeTV;
import XMLNode.XMLNodeDescription;
import XMLNode.XMLNodeProgramme;
import XMLNode.XMLNodeTitle;

/**
 * XMLNode Factory used to parse tags in TV Calendar XML files
 * @author Andrew, Tianyu
 */
public class TVCalXMLNodeFactory extends XMLNodeFactory {

    private static final String DATE_TAG = "programme";
    private static final String TITLE_TAG = "title";
    private static final String DESCRIPTION_TAG = "desc";
    private static final String AUTHOR_TAG = "category";
    private static final String XMLTV_CREDITS_TAG = "credits";

    
    public TVCalXMLNodeFactory() {
        this.setMyXMLNodeNames(myXMLNodeNames);
        this.setMyXMLNodes(myXMLNodes);
    }

    private String[] myXMLNodeNames = { 
                                        DATE_TAG, 
                                        TITLE_TAG,
                                        DESCRIPTION_TAG, 
                                        AUTHOR_TAG,
                                        XMLTV_CREDITS_TAG
                                      };

    private XMLNode[] myXMLNodes = { 
                                        new XMLNodeProgramme(),
                                        new XMLNodeTitle(), 
                                        new XMLNodeDescription(),
                                        new XMLNodeAuthor(),
                                        new XMLNodeTV()
                                   };


    
}
