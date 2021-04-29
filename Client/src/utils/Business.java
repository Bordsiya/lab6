package utils;

import answers.Request;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sun.org.apache.xpath.internal.Arg;
import data.SpaceMarine;
import exceptions.IncorrectCommandException;
import exceptions.IncorrectScriptInputException;
import exceptions.RecursionScriptException;
import exceptions.WrongAmountOfElementsInCommandException;

import java.util.Stack;

/**
 * @author NastyaBordun
 * @version 1.1
 */

public class Business {

    private final Stack<String> scriptNames;

    private AskManager askManager;

    private Console console = null;


    public Business(AskManager askManager){
        this.askManager = askManager;
        scriptNames = new Stack<>();
    }

    public void addConsole(Console console){
        this.console = console;
    }


    private String refactorString(String[] commandArr) throws IncorrectCommandException {
        if(commandArr.length == 0) throw new IncorrectCommandException("Введена некорректная команда");
        String line;
        if(commandArr.length == 1){
            line = "";
        }
        else{
            line = commandArr[1];
        }
        return line;
    }

    public Request makeRequest(String command) throws IncorrectCommandException {
        String[] commandArr = command.trim().split(" ", 2);
        String line = refactorString(commandArr);

        ArgumentState argumentState = analyzeCommand(commandArr[0], line);
        if(argumentState == ArgumentState.ERROR) return null;

        try {
            SpaceMarine spaceMarine = null;
            switch (argumentState) {
                case ADD_OBJECT -> {
                    spaceMarine = addSpaceMarine();
                    return new Request(commandArr[0], line, spaceMarine);
                }
                case UPDATE_OBJECT -> {
                    spaceMarine = updateSpaceMarine();
                    return new Request(commandArr[0], line, spaceMarine);
                }
                case SCRIPT_MODE -> {
                    for(String name : scriptNames){
                        if(name.equals(line)){
                            throw new RecursionScriptException("Ошибка: рекурсивный вызов файла-скрипта");
                        }
                    }
                    scriptNames.push(line);
                    Printer.println("-Начато выполнение скрипта-");
                    console.scriptMode(line);
                    scriptNames.pop();
                    break;
                }
                case EXIT -> console.setWork(false);
            }
        }
        catch (IncorrectScriptInputException | RecursionScriptException e){
            Printer.printError(e.getMessage());
            return null;
        }
        return new Request(commandArr[0], line);
    }

    public ArgumentState analyzeCommand(String command, String arg){
        try {
            switch (command) {
                case "help":
                case "info":
                case "clear":
                case "reorder":
                case "print_field_ascending_weapon_type":
                case "print_field_descending_achievements":
                case "show":
                    if (arg.length() != 0) {
                        throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов в команде");
                    }
                    else return ArgumentState.OK;
                case "remove_greater":
                case "remove_lower":
                case "add":
                    if (arg.length() != 0) {
                        throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов в команде");
                    }
                    else return ArgumentState.ADD_OBJECT;
                case "update":
                    if (arg.length() != 0) {
                        throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов в команде");
                    }
                    else return ArgumentState.UPDATE_OBJECT;
                case "filter_starts_with_achievements":
                case "remove_by_id":
                    String [] commandArr = arg.trim().split(" ");
                    if (arg.length() == 0 || commandArr.length != 1) {
                        throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов в команде");
                    }
                    else return ArgumentState.OK;
                case "execute_script":
                    String [] commandArr1 = arg.trim().split(" ");
                    if(arg.length() == 0 || commandArr1.length != 1){
                        throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
                    }
                    else return ArgumentState.SCRIPT_MODE;
                case "exit":
                    if(arg.length() != 0){
                        throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
                    }
                    else return ArgumentState.EXIT;
                default:
                    throw new IncorrectCommandException("Введена некорректная команда");
            }
        }
        catch (WrongAmountOfElementsInCommandException | IncorrectCommandException e){
            Printer.printError(e.getMessage());
            return ArgumentState.ERROR;
        }
    }

    private SpaceMarine addSpaceMarine() throws IncorrectScriptInputException {
        if(!scriptNames.empty()){
            askManager.setInteractiveMode(false);
        }
        SpaceMarine spaceMarine = new SpaceMarine();
        spaceMarine.setName(askManager.askName());
        spaceMarine.setCoordinates(askManager.askCoordinates());
        spaceMarine.setHealth(askManager.askHealth());
        spaceMarine.setAchievements(askManager.askAchievements());
        spaceMarine.setWeaponType(askManager.askWeaponType());
        spaceMarine.setMeleeWeapon(askManager.askMeleeWeapon());
        spaceMarine.setChapter(askManager.askChapter());
        askManager.setInteractiveMode(true);
        return spaceMarine;
    }

    private SpaceMarine updateSpaceMarine() throws IncorrectScriptInputException {
        if(!scriptNames.empty()){
            askManager.setInteractiveMode(false);
        }
        SpaceMarine spaceMarine = new SpaceMarine();
        if(askManager.questionCheck("name")) spaceMarine.setName(askManager.askName());
        else spaceMarine.setName(null);
        if(askManager.questionCheck("координата x")) spaceMarine.setCoordinateX(askManager.askCoordinateX());
        else spaceMarine.setCoordinateX(992);
        if(askManager.questionCheck("координата y")) spaceMarine.setCoordinateY(askManager.askCoordinateY());
        else spaceMarine.setCoordinateY(null);
        if(askManager.questionCheck("health")) spaceMarine.setHealth(askManager.askHealth());
        else spaceMarine.setHealth(null);
        if(askManager.questionCheck("achievements")) spaceMarine.setAchievements(askManager.askAchievements());
        else spaceMarine.setAchievements(null);
        if(askManager.questionCheck("weaponType")) spaceMarine.setWeaponType(askManager.askWeaponType());
        else spaceMarine.setWeaponType(null);
        if(askManager.questionCheck("meleeWeapon")) spaceMarine.setMeleeWeapon(askManager.askMeleeWeapon());
        else spaceMarine.setMeleeWeapon(null);
        if(askManager.questionCheck("chapterName")) spaceMarine.setChapterName(askManager.askChapterName());
        else spaceMarine.setChapterName(null);
        if(askManager.questionCheck("chapterWorld")) spaceMarine.setChapterWorld(askManager.askChapterWorld());
        else spaceMarine.setChapterWorld(null);
        askManager.setInteractiveMode(true);
        return spaceMarine;
    }
}
