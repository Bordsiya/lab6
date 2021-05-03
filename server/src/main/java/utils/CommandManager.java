package utils;

import commands.CommandBase;
import commands.HelpCommand;
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
        if(help == null){
            return false;
        }
        return help.execute(str, arg);
    }

    public boolean info(String str, Object arg){
        if(info == null){
            return false;
        }
        return info.execute(str, arg);
    }

    public boolean show(String str, Object arg){
        if(show == null){
            return false;
        }
        return show.execute(str, arg);
    }

    public boolean add(String str, Object arg){
        if(add == null){
            return false;
        }
        return add.execute(str, arg);
    }

    public boolean update(String str, Object arg){
        if(update == null){
            return false;
        }
        return update.execute(str, arg);
    }

    public boolean removeById(String str, Object arg){
        if(remove == null){
            return false;
        }
        return remove.execute(str, arg);
    }

    public boolean clear(String str, Object arg){
        if(clear == null){
            return false;
        }
        return clear.execute(str, arg);
    }

    public boolean executeScript(String str, Object arg){
        if(executeScript == null){
            return false;
        }
        return executeScript.execute(str, arg);
    }

    public boolean exit(String str, Object arg){
        if(exit == null){
            return false;
        }
        return exit.execute(str, arg);
    }

    public boolean removeGreater(String str, Object arg){
        if(removeGreater == null){
            return false;
        }
        return removeGreater.execute(str, arg);
    }

    public boolean removeLower(String str, Object arg){
        if(removeLower == null){
            return false;
        }
        return removeLower.execute(str, arg);
    }

    public boolean reorder(String str, Object arg){
        if(reorder == null){
            return false;
        }
        return reorder.execute(str, arg);
    }

    public boolean filterAchievements(String str, Object arg){
        if(filterAchievements == null){
            return false;
        }
        return filterAchievements.execute(str, arg);
    }

    public boolean ascendingWeaponType(String str, Object arg){
        if(ascendingWeaponType == null){
            return false;
        }
        return ascendingWeaponType.execute(str, arg);
    }

    public boolean descendingAchievements(String str, Object arg){
        if(descendingAchievements == null){
            return false;
        }
        return descendingAchievements.execute(str, arg);
    }

}
