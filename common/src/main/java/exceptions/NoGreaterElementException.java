package exceptions;

/**
 * Exception if the collection does not have an element greater than the one entered
 * @author NastyaBordun
 * @version 1.1
 */

public class NoGreaterElementException extends Exception{

    public NoGreaterElementException(String message){
        super(message);
    }

}
