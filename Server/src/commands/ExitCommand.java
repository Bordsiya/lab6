package commands;

import exceptions.WrongAmountOfElementsInCommandException;
import utils.ResponseBuilder;

/**
 * Command for program exiting
 * @author NastyaBordun
 * @version 1.1
 */

public class ExitCommand implements ICommand{
    /**
     * Base for all commands {@link CommandBase}
     */
    private CommandBase commandBase;

    /**
     * Constructor for the command
     * @param commandBase base for commands
     */
    public ExitCommand(CommandBase commandBase){
        this.commandBase = commandBase;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#exit()
     */
    @Override
    public boolean execute(String str, Object arg) {
        try{
            commandBase.exit();
            if(str.length() != 0 || arg != null){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            //console.setWork(false);
            //отправить приказ о завершении?
            return true;
        }
        catch (WrongAmountOfElementsInCommandException e){
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
    }

}
