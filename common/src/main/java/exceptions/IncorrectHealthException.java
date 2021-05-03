package exceptions;

/**
 * Exception for entering health field value of class {@link data.SpaceMarine}, which is lower or equals to 0
 * @author NastyaBordun
 * @version 1.1
 */

public class IncorrectHealthException extends Exception{

    public IncorrectHealthException(String message){
        super(message);
    }

}
