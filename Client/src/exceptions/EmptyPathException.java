package exceptions;

/**
 * Exception for empty filePath field of class {@link utils.FileManager}
 * @author NastyaBordun
 * @version 1.1
 */

public class EmptyPathException extends Exception{
    public EmptyPathException(String message){
        super(message);
    }
}
