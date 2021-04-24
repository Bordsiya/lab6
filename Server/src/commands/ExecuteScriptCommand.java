package commands;

import exceptions.WrongAmountOfElementsInCommandException;
import utils.ResponseBuilder;

/**
 * Command for script executing from the certain file
 * @author NastyaBordun
 * @version 1.1
 */

public class ExecuteScriptCommand implements ICommand{
    /**
     * Base for all commands {@link CommandBase}
     */
    private CommandBase commandBase;

    /**
     * Constructor for the command
     * @param commandBase base for commands
     */
    public ExecuteScriptCommand(CommandBase commandBase){
        this.commandBase = commandBase;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#executeScript()
     */
    @Override
    public boolean execute(String str, Object arg) {
        try{
            commandBase.executeScript();
            String [] commandArr = str.trim().split(" ");
            if(str.length() == 0 || commandArr.length != 1 || arg != null){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            //console.scriptMode(str, console.isWork());
            //отправить команду о смене режима?
            return true;
        }
        catch (WrongAmountOfElementsInCommandException e){
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
    }

}
