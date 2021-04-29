package exceptions;

/**
 * Exception for calling a command with an invalid number of arguments
 * @author NastyaBordun
 * @version 1.1
 */

public class WrongAmountOfElementsInCommandException extends Exception{

    public WrongAmountOfElementsInCommandException(String message){
        super(message);
    }

}
