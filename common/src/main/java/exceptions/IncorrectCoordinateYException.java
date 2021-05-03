package exceptions;

/**
 * Exception for entering y field value of class {@link data.Coordinates}, which is greater than 767
 * @author NastyaBordun
 * @version 1.1
 */

public class IncorrectCoordinateYException extends Exception{

    public IncorrectCoordinateYException(String message){
        super(message);
    }

}
