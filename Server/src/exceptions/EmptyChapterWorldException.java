package exceptions;

/**
 * Exception for empty world field of class {@link data.Chapter}
 * @author NastyaBordun
 * @version 1.1
 */

public class EmptyChapterWorldException extends Exception{

    public EmptyChapterWorldException(String message){
        super(message);
    }

}
