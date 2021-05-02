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
        System.out.println("ResponseAnswer: " + responseAnswer);
        Response response = new Response(responseAnswer, ResponseBuilder.getStringBuilder());
        ResponseBuilder.clear();
        return response;
    }

    private ResponseAnswer analyze(String commandName, String commandStringArg, Object commandObjectArg){
        switch (commandName){
            case "help":
                if(!commandManager.help(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "info":
                if(!commandManager.info(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "show":
                if(!commandManager.show(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "add":
                if(!commandManager.add(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "update":
                if(!commandManager.update(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "remove_by_id":
                if(!commandManager.removeById(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "clear":
                if(!commandManager.clear(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "execute_script":
                if(!commandManager.executeScript(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "exit":
                if(!commandManager.exit(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "remove_greater":
                if(!commandManager.removeGreater(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "remove_lower":
                if(!commandManager.removeLower(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "reorder":
                if(!commandManager.reorder(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "filter_starts_with_achievements":
                if(!commandManager.filterAchievements(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "print_field_ascending_weapon_type":
                if(!commandManager.ascendingWeaponType(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "print_field_descending_achievements":
                if(!commandManager.descendingAchievements(commandStringArg, commandObjectArg)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            default:
                ResponseBuilder.appendError("Команда " + commandName + " не входит в список доступных");
                return ResponseAnswer.ERROR;
        }

    }
}
