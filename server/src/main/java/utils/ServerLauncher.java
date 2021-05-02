package utils;

import commands.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class ServerLauncher {
    public void launchServer(){
        FileManager fileManager = new FileManager("VAR");
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
        int port = 894;
        SocketAddress socketAddress = new InetSocketAddress(port);
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            Server server = new Server(messageAnalyzator, port);
            System.out.println("Все создано, запускаем");
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
