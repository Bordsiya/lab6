package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import data.SpaceMarine;
import exceptions.EmptyPathException;
import exceptions.IncorrectPathException;
import exceptions.ReadFileException;
import javafx.scene.chart.ScatterChart;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
