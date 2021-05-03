package commands;

import exceptions.EmptyCollectionException;
import exceptions.LoadCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import utils.ResponseBuilder;

/**
 * Command for printing all of collection elements
 * @author NastyaBordun
 * @version 1.1
 */

public class ShowCommand implements ICommand{
    /**
     * Base for all commands {@link CommandBase}
     */
    private CommandBase commandBase;
    /**
     * Manager for collection {@link CollectionManager}
     */
    private CollectionManager collectionManager;
    /**
     * Constructor for the command
     * @param commandBase base for commands
     * @param collectionManager collection manager
     */
    public ShowCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#show()
     * @see CollectionManager#getStringCollection()
     */
    @Override
    public boolean execute(String str, Object arg) {
        try{
            commandBase.show();
            if(str.length() != 0 || arg!=null){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.getCollection() == null){
                throw new LoadCollectionException("Файл недоступен");
            }
            ResponseBuilder.appendln("Содержание коллекции:");
            ResponseBuilder.append(collectionManager.getStringCollection());
            return true;
        }
        catch (WrongAmountOfElementsInCommandException | EmptyCollectionException | LoadCollectionException e){
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }

    }

}
