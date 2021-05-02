package answers;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName = "";
    private String commandStringArg = "";
    private Serializable commandObjectArg = null;

    public Request(String commandName, String commandStringArg, Serializable commandObjectArg){
        this.commandName = commandName;
        this.commandStringArg = commandStringArg;
        this.commandObjectArg = commandObjectArg;
    }

    public Request(String commandName, String commandStringArg){
        this.commandName = commandName;
        this.commandStringArg = commandStringArg;
        this.commandObjectArg = null;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandStringArg() {
        return commandStringArg;
    }

    public Serializable getCommandObjectArg() {
        return commandObjectArg;
    }

    public boolean isEmpty(){
        return commandName.isEmpty() && commandStringArg.isEmpty() && commandObjectArg == null;
    }

    @Override
    public String toString() {
        return "Запрос:\n" + "Название команды: " + this.getCommandName() + "\nТекстовый аргумент: "
                + this.getCommandStringArg() + "\nАргумент-объект: " + this.commandObjectArg + "\n";
    }
}
