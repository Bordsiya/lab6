package commands;

import data.SpaceMarine;
import exceptions.NoLowerElementException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import utils.ResponseBuilder;

/**
 * The Removal Command for collection elements with type {@link SpaceMarine}, lower than assigned element
 * @author NastyaBordun
 * @version 1.1
 */

public class RemoveLowerCommand implements ICommand{
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
    public RemoveLowerCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#removeLower()
     * @see CollectionManager#removeLower(SpaceMarine)
     */
    @Override
    public boolean execute(String str, Object arg) {
        try{
            commandBase.removeLower();
            if(str.length() != 0 || arg == null){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            SpaceMarine spaceMarine = (SpaceMarine)arg;

            if(!collectionManager.removeLower(spaceMarine)){
                throw new NoLowerElementException("В коллекции нет элемента, меньше введенного");
            }
            ResponseBuilder.appendln("Все меньшие элементы коллекции удалены");
            return true;
        }
        catch (WrongAmountOfElementsInCommandException | NoLowerElementException e){
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
        catch (ClassCastException e){
            ResponseBuilder.appendError("Переданный клиентом объект некорректен");
            return false;
        }
    }

}
