package utils;

import commands.ICommand;

/**
 * Class for command announcement
 * @author NastyaBordun
 * @version 1.1
 */

public class CommandManager {

    private final ICommand help;
    private ICommand info;
    private ICommand show;
    private ICommand add;
    private ICommand update;
    private ICommand remove;
    private ICommand clear;
    private ICommand save;
    private ICommand executeScript;
    private ICommand exit;
    private ICommand removeGreater;
    private ICommand removeLower;
    private ICommand reorder;
    private ICommand filterAchievements;
    private ICommand ascendingWeaponType;
    private ICommand descendingAchievements;

    public CommandManager(ICommand help,
                          ICommand info,
                          ICommand show,
                          ICommand add,
                          ICommand update,
                          ICommand remove,
                          ICommand clear,
                          ICommand save,
                          ICommand executeScript,
                          ICommand exit,
                          ICommand removeGreater,
                          ICommand removeLower,
                          ICommand reorder,
                          ICommand filterAchievements,
                          ICommand ascendingWeaponType,
                          ICommand descendingAchievements){
        this.help = help;
        this.info = info;
        this.show = show;
        this.add = add;
        this.update = update;
        this.remove = remove;
        this.clear = clear;
        this.save = save;
        this.executeScript = executeScript;
        this.exit = exit;
        this.removeGreater = removeGreater;
        this.removeLower = removeLower;
        this.reorder = reorder;
        this.filterAchievements = filterAchievements;
        this.ascendingWeaponType = ascendingWeaponType;
        this.descendingAchievements = descendingAchievements;
    }

    public boolean help(String str, Object arg){
        return help.execute(str, arg);
    }

    public boolean info(String str, Object arg){
        return info.execute(str, arg);
    }

    public boolean show(String str, Object arg){
        return show.execute(str, arg);
    }

    public boolean add(String str, Object arg){
        return add.execute(str, arg);
    }

    public boolean update(String str, Object arg){
        return update.execute(str, arg);
    }

    public boolean removeById(String str, Object arg){
        return remove.execute(str, arg);
    }

    public boolean clear(String str, Object arg){
        return clear.execute(str, arg);
    }

    public boolean save(String str, Object arg){
        return save.execute(str, arg);
    }

    public boolean executeScript(String str, Object arg){
        return executeScript.execute(str, arg);
    }

    public boolean exit(String str, Object arg){
        return exit.execute(str, arg);
    }

    public boolean removeGreater(String str, Object arg){
        return removeGreater.execute(str, arg);
    }

    public boolean removeLower(String str, Object arg){
        return removeLower.execute(str, arg);
    }

    public boolean reorder(String str, Object arg){
        return reorder.execute(str, arg);
    }

    public boolean filterAchievements(String str, Object arg){
        return filterAchievements.execute(str, arg);
    }

    public boolean ascendingWeaponType(String str, Object arg){
        return ascendingWeaponType.execute(str, arg);
    }

    public boolean descendingAchievements(String str, Object arg){
        return descendingAchievements.execute(str, arg);
    }

}
