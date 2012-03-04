package exceptions;

/**
 * Exception object thrown when filtering fails
 * @author James
 */
public class FilterException extends Exception {
    
    public FilterException(String message){
        super(message);
    }

}
