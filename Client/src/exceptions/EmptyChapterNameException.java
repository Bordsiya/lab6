package exceptions;

/**
 * Exception for empty name field of class {@link data.Chapter}
 * @author NastyaBordun
 * @version 1.1
 */
public class EmptyChapterNameException extends Exception{

    public EmptyChapterNameException(String message){
        super(message);
    }

}
