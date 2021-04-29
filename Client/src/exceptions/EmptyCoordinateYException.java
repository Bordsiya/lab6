package exceptions;

/**
 * Exception for empty y field of class {@link data.Coordinates}
 * @author NastyaBordun
 * @version 1.1
 */

public class EmptyCoordinateYException extends Exception{

    public EmptyCoordinateYException(String message){
        super(message);
    }

}
