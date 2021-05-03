package exceptions;

/**
 * Exception for entering a non-existent value (not 1 and not 2) while selecting for update
 * @author NastyaBordun
 * @version 1.1
 */

public class IncorrectAnswerException extends Exception{

    public IncorrectAnswerException(String message){
        super(message);
    }

}
