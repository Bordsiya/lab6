package exceptions;

/**
 * Exception for entering non-valid command
 * @author NastyaBordun
 * @version 1.1
 */

public class IncorrectCommandException extends Exception{

    public IncorrectCommandException(String message){
        super(message);
    }

}
