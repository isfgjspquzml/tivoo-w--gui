package exceptions;


/**
 * Exception object thrown when HTML output fails
 * @author James
 */
public class HTMLException extends Exception {
    
    public HTMLException (String message){
        super(message);
    }

}
