package utils;

public class ResponseBuilder {
    private static String stringBuilder = "";

    public static void append(String ans){
        stringBuilder += ans;
    }

    public static void appendln(String ans){
        stringBuilder += ans;
        stringBuilder += '\n';
    }

    public static void appendError(String ans){
        stringBuilder += "Error: " + ans + '\n';
    }

    public static String getStringBuilder(){
        return stringBuilder;
    }

    public static void clear(){
        stringBuilder = "";
    }
}
