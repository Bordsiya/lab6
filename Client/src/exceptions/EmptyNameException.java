package exceptions;

/**
 * Exception for empty name field of class {@link data.SpaceMarine}
 * @author NastyaBordun
 * @version 1.1
 */

public class EmptyNameException extends Exception{

    public EmptyNameException(String message){
        super(message);
    }

}
