package utils;

import exceptions.EmptyPathException;
import exceptions.IncorrectPathException;
import exceptions.ReadFileException;

import java.io.File;

/**
 * Class for files uploading
 * @author NastyaBordun
 * @version 1.1
 */

public class FileManager {

    public File getScriptFile(String path){
        try{
            if(path != null) {
                File file = new File(path);
                if (file.exists()) {
                    if (file.canRead()) {
                        return file;
                    }
                    else throw new ReadFileException("Файл невозможно прочитать");
                }
                else throw new IncorrectPathException("Указан неправильный путь до скрипта");
            }
            else throw new EmptyPathException("Путь до файла скрипта не указан");
        }
        catch (ReadFileException | IncorrectPathException | EmptyPathException e){
            Printer.printError(e.getMessage());
            return null;
        }

    }



}
