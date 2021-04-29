package exceptions;

/**
 * Exception for interactive communication with script
 * @author NastyaBordun
 * @version 1.1
 */

public class IncorrectScriptInputException extends Exception{

    public IncorrectScriptInputException(String message){
        super(message);
    }

}
