package exceptions;

/**
 * Exception for non-saved collection
 * @author NastyaBordun
 * @version 1.1
 */

public class NullLastSaveException extends Exception{

    public NullLastSaveException(String message){
        super(message);
    }

}
