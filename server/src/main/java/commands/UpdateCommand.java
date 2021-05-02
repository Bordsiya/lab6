package commands;

import data.*;
import exceptions.SpaceMarineNotFoundException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import utils.ResponseBuilder;

import java.time.LocalDateTime;

/**
 * The Update Value Command of a Collection Element by ID
 * @author NastyaBordun
 * @version 1.1
 */

public class UpdateCommand implements ICommand{
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
    public UpdateCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#update()
     * @see CollectionManager#searchById(Integer)
     * @see SpaceMarine#setName(String)
     * @see SpaceMarine#setCoordinateX(long)
     * @see SpaceMarine#setCoordinateY(Double)
     * @see SpaceMarine#setHealth(Float)
     * @see SpaceMarine#setAchievements(String)
     * @see SpaceMarine#setWeaponType(Weapon)
     * @see SpaceMarine#setMeleeWeapon(MeleeWeapon)
     * @see SpaceMarine#setChapterName(String)
     * @see SpaceMarine#setChapterWorld(String)
     */
    @Override
    public boolean execute(String str, Object arg) {
        try{
            commandBase.update();
            String [] commandArr = str.split(" ");
            if(str.length() == 0 || commandArr.length != 1 || arg == null){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.searchById(Integer.parseInt(commandArr[0])) == null){
                throw new SpaceMarineNotFoundException("Не найден космический корабль с таким ID");
            }
            SpaceMarine collectionSpaceMarine = collectionManager.searchById(Integer.parseInt(commandArr[0]));
            SpaceMarine newMarine = (SpaceMarine) arg;

            String name = newMarine.getName() == null ? collectionSpaceMarine.getName() : newMarine.getName();
            Coordinates coordinates = newMarine.getCoordinates() == null ? collectionSpaceMarine.getCoordinates() : newMarine.getCoordinates();
            LocalDateTime creationDate = collectionSpaceMarine.getCreationDate();
            Float health = newMarine.getHealth() == null ? collectionSpaceMarine.getHealth() : newMarine.getHealth();
            String achievements = newMarine.getAchievements() == null ? collectionSpaceMarine.getAchievements() : newMarine.getAchievements();
            Weapon weaponType = newMarine.getWeaponType() == null ? collectionSpaceMarine.getWeaponType() : newMarine.getWeaponType();
            MeleeWeapon meleeWeapon = newMarine.getMeleeWeapon() == null ? collectionSpaceMarine.getMeleeWeapon() : newMarine.getMeleeWeapon();
            Chapter chapter = newMarine.getChapter() == null ? collectionSpaceMarine.getChapter() : newMarine.getChapter();

            collectionSpaceMarine.setName(name);
            collectionSpaceMarine.setCoordinates(coordinates);
            collectionSpaceMarine.setCreationDate(creationDate);
            collectionSpaceMarine.setHealth(health);
            collectionSpaceMarine.setAchievements(achievements);
            collectionSpaceMarine.setWeaponType(weaponType);
            collectionSpaceMarine.setMeleeWeapon(meleeWeapon);
            collectionSpaceMarine.setChapter(chapter);

            ResponseBuilder.appendln("Значение элемента обновлено");
            return true;
        }
        catch (WrongAmountOfElementsInCommandException | SpaceMarineNotFoundException e){
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
        catch (NumberFormatException e){
            ResponseBuilder.appendError("ID должно являться числом");
            return false;
        }
        catch (ClassCastException e){
            ResponseBuilder.appendError("Переданный клиентом объект некорректен");
            return false;
        }
    }

}
