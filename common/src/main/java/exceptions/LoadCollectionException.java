package exceptions;

/**
 * Exception for wrong collection in script
 * @author NastyaBordun
 * @version 1.1
 */
public class LoadCollectionException extends Exception{

    public LoadCollectionException(String message){
        super(message);
    }
}
