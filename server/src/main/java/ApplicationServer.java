import utils.ServerLauncher;

/**
 * Main Server app
 * @author NastyaBordun
 * @version 1.1
 */
public class ApplicationServer {
    public static void main(String[] args) {
        ServerLauncher serverLauncher = new ServerLauncher();
        serverLauncher.launchServer();
    }
}
