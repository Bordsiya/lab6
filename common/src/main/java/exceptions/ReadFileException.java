package exceptions;

/**
 * Exception for reading file
 * @author NastyaBordun
 * @version 1.1
 */
public class ReadFileException extends Exception{
    public ReadFileException(String message){
        super(message);
    }
}
