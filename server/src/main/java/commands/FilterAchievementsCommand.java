package commands;

import data.SpaceMarine;
import exceptions.EmptyCollectionException;
import exceptions.NoMatchException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import utils.ResponseBuilder;

import java.util.ArrayList;

/**
 * Printing command for collection elements with type {@link SpaceMarine}, whose achievements field value starts with the specified substring
 * @author NastyaBordun
 * @version 1.1
 */

public class FilterAchievementsCommand implements ICommand{
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
    public FilterAchievementsCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#filterAchievements()
     * @see CollectionManager#startsWithAchievements(String)
     */
    @Override
    public boolean execute(String str, Object arg) {
        try{
            commandBase.filterAchievements();
            String [] commandArr = str.trim().split(" ");
            if(str.length() == 0 || commandArr.length != 1 || arg != null){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.collectionSize() == 0){
                throw new EmptyCollectionException("Коллекция пуста");
            }
            ArrayList<SpaceMarine> spaceMarines = collectionManager.startsWithAchievements(str);
            if(spaceMarines.size() == 0){
                throw new NoMatchException("Совпадения не найдено");
            }
            String info = "";
            for(SpaceMarine sm : spaceMarines){
                info += "Космический корабль\n" + sm.toString() + "\n";
            }
            ResponseBuilder.append(info);
            ResponseBuilder.appendln("Все нужные члены коллекции выведены");
            return true;
        }
        catch (EmptyCollectionException | NoMatchException | WrongAmountOfElementsInCommandException e){
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }

    }

}
