package utils;

public class Printer {

    public static void print(String str){
        System.setProperty("file.encoding", "UTF-8");
        System.out.print(str);
    }

    public static void println(String str){
        System.setProperty("file.encoding", "UTF-8");
        System.out.println(str);
    }

    public static void printError(String str){
        System.setProperty("file.encoding", "UTF-8");
        System.out.println("Error: " + str);
    }

}
