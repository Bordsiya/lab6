package utils;

import answers.Request;
import answers.Response;
import answers.ResponseAnswer;

public class MessageAnalyzator {
    private CommandManager commandManager;

    public MessageAnalyzator(CommandManager commandManager){
        this.commandManager = commandManager;
    }

    public Response handle(Request request){
        ResponseAnswer responseAnswer = analyze(request.getCommandName(), request.getCommandStringArg(), request.getCommandObjectArg());
        Response response = new Response(responseAnswer, ResponseBuilder.getStringBuilder());
        ResponseBuilder.clear();
        return response;
    }

    private ResponseAnswer analyze(String commandName, String commandStringArg, Object commandObjectArg){
        switch (commandName){
            case "HELP":
                if(!commandManager.help(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "INFO":
                if(!commandManager.info(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "SHOW":
                if(!commandManager.show(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "ADD":
                if(!commandManager.add(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "UPDATE":
                if(!commandManager.update(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "REMOVE_BY_ID":
                if(!commandManager.removeById(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "CLEAR":
                if(!commandManager.clear(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "EXECUTE_SCRIPT":
                if(!commandManager.executeScript(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "EXIT":
                if(!commandManager.exit(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "REMOVE_GREATER":
                if(!commandManager.removeGreater(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "REMOVE_LOWER":
                if(!commandManager.removeLower(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "REORDER":
                if(!commandManager.reorder(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "FILTER_STARTS_WITH_ACHIEVEMENTS":
                if(!commandManager.filterAchievements(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "PRINT_FIELD_ASCENDING_WEAPON_TYPE":
                if(!commandManager.ascendingWeaponType(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "PRINT_FIELD_DESCENDING_ACHIEVEMENTS":
                if(!commandManager.descendingAchievements(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            default:
                ResponseBuilder.appendError("Команда " + commandName + " не входит в список доступных");
                return ResponseAnswer.ERROR;
        }

    }
}
