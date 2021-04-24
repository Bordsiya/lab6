package commands;

/**
 * Interface for commands
 * @author NastyaBordun
 * @version 1.1
 */

public interface ICommand {

    public boolean execute(String str, Object arg);

}
