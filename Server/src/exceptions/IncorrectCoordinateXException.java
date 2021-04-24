package exceptions;

/**
 * Exception for entering x field value of class {@link data.Coordinates}, which is greater than 991
 * @author NastyaBordun
 * @version 1.1
 */

public class IncorrectCoordinateXException extends Exception{

    public IncorrectCoordinateXException(String message){
        super(message);
    }

}
