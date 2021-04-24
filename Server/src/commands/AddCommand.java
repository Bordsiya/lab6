package commands;

import data.*;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import utils.ResponseBuilder;

/**
 * The command for adding a new element to the collection
 * @author NastyaBordun
 * @version 1.1
 */

public class AddCommand implements ICommand{
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
    public AddCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#add()
     * @see CollectionManager#addElement(SpaceMarine)
     */
    @Override
    public boolean execute(String str, Object arg) {
        try{
            commandBase.add();
            if(str.length() != 0 || arg == null){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            SpaceMarine newSpaceMarine = (SpaceMarine)arg;
            collectionManager.addElement(newSpaceMarine);
            ResponseBuilder.appendln("Элемент добавлен в коллекцию");
            return true;
        }
        catch (WrongAmountOfElementsInCommandException e){
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
        catch (ClassCastException e){
            ResponseBuilder.appendError("Переданный клиентом объект некорректен");
            return false;
        }
    }

}
