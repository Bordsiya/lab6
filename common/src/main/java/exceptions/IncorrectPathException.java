package exceptions;

/**
 * Exception for incorrect path to file
 * @author NastyaBordun
 * @version 1.1
 */
public class IncorrectPathException extends Exception{

    public IncorrectPathException(String message){
            super(message);
        }
}
