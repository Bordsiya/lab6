package exceptions;

/**
 * Exception if there is no collection elements with type {@link data.SpaceMarine}, with field achievements values starts with entered substring
 * @author NastyaBordun
 * @version 1.1
 */

public class NoMatchException extends Exception{

    public NoMatchException(String message){
        super(message);
    }

}
