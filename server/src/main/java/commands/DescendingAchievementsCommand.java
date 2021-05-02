package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import utils.ResponseBuilder;

/**
 * Printing command for field achievements of all collection elements, with types {@link data.SpaceMarine}, in descending order
 * @author NastyaBordun
 * @version 1.1
 */

public class DescendingAchievementsCommand implements ICommand{
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
    public DescendingAchievementsCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#descendingAchievements()
     * @see CollectionManager#collectionSize()
     * @see CollectionManager#descendAchievements()
     */
    @Override
    public boolean execute(String str, Object arg) {
        try{
            commandBase.descendingAchievements();
            if(str.length() != 0 || arg != null){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.collectionSize() == 0){
                throw new EmptyCollectionException("Коллекция пуста");
            }
            String info = collectionManager.descendAchievements();
            ResponseBuilder.append(info);
            return true;
        }
        catch (EmptyCollectionException | WrongAmountOfElementsInCommandException e){
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
    }

}
