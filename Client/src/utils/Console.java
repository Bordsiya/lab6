package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Working environment for command line reading
 * @author NastyaBordun
 * @version 1.1
 */

public class Console {

    private boolean work;

    private BufferedReader reader;

    private FileManager fileManager;

    private Client client;

    private AskManager askManager;


    public Console(BufferedReader reader, FileManager fileManager, Client client, AskManager askManager){
        this.reader = reader;
        this.work = true;
        this.fileManager = fileManager;
        this.client = client;
        this.askManager = askManager;
    }


    public void setWork(boolean work){
        this.work = work;
    }


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

    public void scriptMode(String path){
        File scriptFile = fileManager.getScriptFile(path);
        if(scriptFile != null){
            FileInputStream file = null;
            try {
                file = new FileInputStream(scriptFile);
                BufferedInputStream bf2 = new BufferedInputStream(file);
                BufferedReader r2 = new BufferedReader(new InputStreamReader(bf2, StandardCharsets.UTF_8));
                String line = r2.readLine().trim();
                askManager.addScriptReader(r2);
                while(line != null && this.work){
                    line = r2.readLine().trim();
                    client.handle(line);
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
