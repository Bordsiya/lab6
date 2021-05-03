package commands;

import exceptions.EmptyCollectionException;
import exceptions.LoadCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import utils.ResponseBuilder;

/**
 * Command for collection clearing
 * @author NastyaBordun
 * @version 1.1
 */

public class ClearCommand implements ICommand{
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
    public ClearCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#clear()
     * @see CollectionManager#collectionSize()
     * @see CollectionManager#clearCollection()
     */
    @Override
    public boolean execute(String str, Object arg) {
        try{
            commandBase.clear();
            if(str.length() != 0 || arg != null){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.getCollection() == null){
                throw new LoadCollectionException("Файл недоступен");
            }
            if(collectionManager.collectionSize() == 0){
                throw new EmptyCollectionException("Коллекция пуста");
            }
            collectionManager.clearCollection();
            ResponseBuilder.appendln("Коллекция очищена");
            return true;
        }
        catch (EmptyCollectionException | WrongAmountOfElementsInCommandException | LoadCollectionException e){
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
    }

}
