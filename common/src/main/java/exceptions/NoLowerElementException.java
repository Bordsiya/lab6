package exceptions;

/**
 * Exception if the collection does not have an element lower than the one entered
 * @author NastyaBordun
 * @version 1.1
 */

public class NoLowerElementException extends Exception{

    public NoLowerElementException(String message){
        super(message);
    }

}
