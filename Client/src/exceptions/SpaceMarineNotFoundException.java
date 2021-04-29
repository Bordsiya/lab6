package exceptions;

/**
 * Exception if an element with a specified ID does not appear in the collection
 * @author NastyaBordun
 * @version 1.1
 */

public class SpaceMarineNotFoundException extends Exception{

    public SpaceMarineNotFoundException(String message){
        super(message);
    }

}
