package utils;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import data.SpaceMarine;

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

public class  FileManager {
    private String env = null;

    public FileManager(String env){
        this.env = env;
    }

    public void writeCollection(Collection<SpaceMarine> collection){
        String path = System.getenv(env);
        if(path != null){
            File file = new File(path);
            if(file.exists()){
                if(file.canWrite()){
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JavaTimeModule javaTimeModule = new JavaTimeModule();
                        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        mapper.registerModule(javaTimeModule);
                        mapper.writeValue(file, collection);
                        ResponseBuilder.appendln("Коллекция успешно загружена");
                    } catch (IOException e) {
                        ResponseBuilder.appendError("Ошибка ввода/вывода");
                    }
                }
                else{
                    ResponseBuilder.appendError("Недостаточно прав доступа");
                }
            }
            else{
                ResponseBuilder.appendError("Путь до файла некорректен");
            }
        }
        else{
            ResponseBuilder.appendError("Не удалось получить путь из переменной окружения");
        }
    }

    public Stack<SpaceMarine> readCollection(){
        String path = System.getenv(env);
        if(path != null){
            File file = new File(path);
            if(file.exists()){
                if(file.canRead()){
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
                        JavaTimeModule javaTimeModule = new JavaTimeModule();
                        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        mapper.registerModule(javaTimeModule);
                        SpaceMarine [] spaceMarines = mapper.readValue(file, SpaceMarine[].class);
                        Stack<SpaceMarine> collection = new Stack<>();
                        for(SpaceMarine sm : spaceMarines){
                            collection.push(sm);
                            /*
                            PrintWriter consoleOut = new PrintWriter(new OutputStreamWriter(System.out, "utf-8"));
                            consoleOut.println(".....Элемент в fileManager: " + sm.toString());
                            consoleOut.flush();
                            consoleOut.close();


                             */


                        }
                        ResponseBuilder.appendln("Коллекция успешно загружена");
                        return  collection;
                    } catch (IOException e) {
                        ResponseBuilder.appendError("Ошибка ввода/вывода");
                        return new Stack<>();
                    }
                }
                else{
                    ResponseBuilder.appendError("Недостаточно прав доступа");
                    return new Stack<>();
                }
            }
            else{
                ResponseBuilder.appendError("Путь до файла некорректен");
                return new Stack<>();
            }
        }
        else{
            ResponseBuilder.appendError("Не удалось получить путь из переменной окружения");
            return new Stack<>();
        }
    }

    public Queue<String> readScriptFile(String path){
        if(path != null){
            File file = new File(path);
            if(file.exists()){
                if(file.canRead()){
                    FileInputStream fileStream = null;
                    try {
                        fileStream = new FileInputStream(file);
                        BufferedInputStream bf2 = new BufferedInputStream(fileStream);
                        BufferedReader r2 = new BufferedReader(new InputStreamReader(bf2, StandardCharsets.UTF_8));
                        Queue<String> textCommands = new LinkedList<>();
                        String line = r2.readLine().trim();
                        while(line != null){
                            textCommands.add(line);
                            line = r2.readLine().trim();
                        }
                        return textCommands;
                    }
                    catch (FileNotFoundException e){
                        return new LinkedList<>();
                    } catch (IOException e) {
                        return new LinkedList<>();
                    }
                }
            }
        }
        return new LinkedList<>();
    }



}
