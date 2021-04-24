package commands;

import utils.ResponseBuilder;

/**
 * Base for all commands
 * @author NastyaBordun
 * @version 1.1
 */

public class CommandBase {

    public void help(){
        ResponseBuilder.appendln("Вызвана команда help");
    }

    public void info(){
        ResponseBuilder.appendln("Вызвана команда info");
    }

    public void show(){
        ResponseBuilder.appendln("Вызвана команда show");
    }

    public void add(){
        ResponseBuilder.appendln("Вызвана команда add");
    }

    public void update(){
        ResponseBuilder.appendln("Вызвана команда update");
    }

    public void remove(){
        ResponseBuilder.appendln("Вызвана команда remove_by_id");
    }

    public void clear(){
        ResponseBuilder.appendln("Вызвана команда clear");
    }

    public void save(){
        ResponseBuilder.appendln("Вызвана команда save");
    }

    public void executeScript(){
        ResponseBuilder.appendln("Вызвана команда execute_script");
    }

    public void exit(){
        ResponseBuilder.appendln("Вызвана команда exit");
    }

    public void removeGreater(){
        ResponseBuilder.appendln("Вызвана команда remove_greater");
    }

    public void removeLower(){
        ResponseBuilder.appendln("Вызвана команда remove_lower");
    }

    public void reorder(){
        ResponseBuilder.appendln("Вызвана команда reorder");
    }

    public void filterAchievements(){
        ResponseBuilder.appendln("Вызвана команда filter_starts_with_achievements");
    }

    public void ascendingWeaponType(){
        ResponseBuilder.appendln("Вызвана команда print_field_ascending_weapon_type");
    }

    public void descendingAchievements(){
        ResponseBuilder.appendln("Вызвана команда print_field_descending_achievements");
    }

}
