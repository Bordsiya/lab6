package commands;

import exceptions.LoadCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import utils.ResponseBuilder;

import java.io.IOException;

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
    private CollectionManager collectionManager;

    /**
     * Constructor for the command
     * @param commandBase base for commands
     */
    public ExitCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
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
            if(collectionManager.getCollection() == null){
                throw new LoadCollectionException("Файл недоступен, невозможно сохранить коллекцию");
            }
            collectionManager.saveCollection();
            return true;
        }
        catch (WrongAmountOfElementsInCommandException | LoadCollectionException e){
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
        catch (IOException e){
            ResponseBuilder.appendError("Ошибка ввода/вывода");
            return false;
        }
    }

}
