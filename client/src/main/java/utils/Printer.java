package utils;

/**
 * Class for console printing
 * @author NastyaBordun
 * @version 1.1
 */
public class Printer {

    /**
     * Print without ln
     * @param str string
     */
    public static void print(String str){
        System.out.print(str);
    }

    /**
     * Print with ln
     * @param str string
     */
    public static void println(String str){
        System.out.println(str);
    }

    /**
     * Print Error
     * @param str string
     */
    public static void printError(String str){
        System.out.println("Error: " + str);
    }

}
