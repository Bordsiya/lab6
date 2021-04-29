package exceptions;

/**
 * Exception when a collection element cannot be removed from a specified ID
 * @author NastyaBordun
 * @version 1.1
 */

public class ObjectDoesNotExistException extends Exception{

    public ObjectDoesNotExistException(String message){
        super(message);
    }

}
