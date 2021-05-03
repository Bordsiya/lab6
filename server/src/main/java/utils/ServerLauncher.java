package utils;

import commands.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for server launching
 * @author NastyaBordun
 * @version 1.1
 */
public class ServerLauncher {
    public static final int PORT = 12831;
    public static final String ENV = "VAR";
    public static Logger logger = Logger.getLogger("ServerLogger");

    public void launchServer(){
            FileManager fileManager = new FileManager(ENV);
            CommandBase commandBase = new CommandBase();
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                    new HelpCommand(commandBase),
                    new InfoCommand(commandBase, collectionManager),
                    new ShowCommand(commandBase, collectionManager),
                    new AddCommand(commandBase, collectionManager),
                    new UpdateCommand(commandBase, collectionManager),
                    new RemoveCommand(commandBase, collectionManager),
                    new ClearCommand(commandBase, collectionManager),
                    new ExecuteScriptCommand(commandBase),
                    new ExitCommand(commandBase, collectionManager),
                    new RemoveGreaterCommand(commandBase, collectionManager),
                    new RemoveLowerCommand(commandBase, collectionManager),
                    new ReorderCommand(commandBase, collectionManager),
                    new FilterAchievementsCommand(commandBase, collectionManager),
                    new AscendingWeaponTypeCommand(commandBase, collectionManager),
                    new DescendingAchievementsCommand(commandBase, collectionManager));

            MessageAnalyzator messageAnalyzator = new MessageAnalyzator(commandManager);
            Server server = new Server(messageAnalyzator, collectionManager);
            logger.log(Level.INFO, "Сервер запущен");
            server.run();

    }
}
