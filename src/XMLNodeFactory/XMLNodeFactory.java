package XMLNodeFactory;

import java.util.Arrays;
import java.util.List;

import XMLNode.XMLNode;

/**
 * Creates the proper XMLNode based on which type of XML tag is being parsed
 * @author Andrew, Tianyu
 */
public abstract class XMLNodeFactory {

    private int myXMLNodeIndex;
    
    private String[] myXMLNodeNames;
    
    private List<String> myXMLNodeNamesList;
    
    private XMLNode[] myXMLNodes;
    
    
    public void setMyXMLNodeNames (String[] XMLNodeNames)
    {
        myXMLNodeNames = XMLNodeNames;
        myXMLNodeNamesList = Arrays.asList(myXMLNodeNames);
    }
    
    
    public void setMyXMLNodeIndex (int XMLNodeIndex)
    {
        myXMLNodeIndex = XMLNodeIndex;
    }
    
    
    public void setMyXMLNodes (XMLNode[] XMLNodes)
    {
        myXMLNodes = XMLNodes;
    }
    
    
    public void setXMLNodeIndex(String qName)
    {        
        myXMLNodeIndex = myXMLNodeNamesList.indexOf(qName);
    }
    
    
    public String getXMLNodeName ()
    {
        return myXMLNodeNames[myXMLNodeIndex];
    }
 
    
    public XMLNode getXMLNodeType ()
    {
        return myXMLNodes[myXMLNodeIndex];
    }
    
    
    public int getXMLNodeIndex ()
    {
        return myXMLNodeIndex;
    }
    
    
    public int getXMLNodeIndexOf (String qName)
    {
        return myXMLNodeNamesList.indexOf(qName);
    }
   
   
}
