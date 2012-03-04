package exceptions;

/**
 * Exception object thrown when XML parsing fails
 * @author James
 */
public class XMLException extends Exception {
    
    public XMLException (String message){
        super(message);
    }

}
