package exceptions;

/**
 * Exception for recursive script calling
 * @author NastyaBordun
 * @version 1.1
 */

public class RecursionScriptException extends Exception{

    public RecursionScriptException(String message){
        super(message);
    }

}
