package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Working environment for command line reading
 * @author NastyaBordun
 * @version 1.1
 */

public class Console {

    /**
     * Variable to identify the need for further work
     */
    private boolean work;
    /**
     * Reader for line reading support {@link BufferedReader#readLine()}
     */
    private BufferedReader reader;
    /**
     * For script reading {@link FileManager}
     */
    private FileManager fileManager;

    /**
     * For requests handling {@link Client}
     */
    private Client client;

    /**
     * For adding scriptModes {@link AskManager}
     */
    private AskManager askManager;


    /**
     * Constructor for class
     * @param reader Reader for line reading support {@link BufferedReader#readLine()}
     * @param fileManager For script reading {@link FileManager}
     * @param client For requests handling {@link Client}
     * @param askManager For adding scriptModes {@link AskManager}
     */
    public Console(BufferedReader reader, FileManager fileManager, Client client, AskManager askManager){
        this.reader = reader;
        this.work = true;
        this.fileManager = fileManager;
        this.client = client;
        this.askManager = askManager;
    }


    /**
     * Setting working state
     * @param work needful state
     */
    public void setWork(boolean work){
        this.work = work;
    }

    /**
     * Work in the interactive mode
     */
    public void interactiveMode(){
        while(this.work){
            try{
                String command = this.reader.readLine().trim();
                client.handle(command);
            }
            catch (IOException e){
                Printer.println("Ошибка ввода");
            }
        }
    }
    /**
     * Work with a script
     * @param path path to file passed by chooseCommand method
     * @see FileManager
     */
    public void scriptMode(String path){
        File scriptFile = fileManager.getScriptFile(path);
        if(scriptFile != null){
            FileInputStream file = null;
            try {
                file = new FileInputStream(scriptFile);
                BufferedInputStream bf2 = new BufferedInputStream(file);
                BufferedReader r2 = new BufferedReader(new InputStreamReader(bf2, StandardCharsets.UTF_8));
                String line = r2.readLine().trim();
                while(line != null && this.work){
                    Printer.println("Команда из скриптов: " + line.trim());
                    if(askManager.getBf2() != r2 || askManager.getBf2() == null) askManager.addScriptReader(r2);

                    client.handle(line.trim());

                    line = r2.readLine();
                }
            } catch (FileNotFoundException e) {
                Printer.printError("Файл не найден");
            } catch (IOException e) {
                Printer.printError("Ошибка ввода/вывода");
            }
        }
        setWork(true);
    }



}
