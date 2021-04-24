package utils;

public class ResponseBuilder {
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void append(String ans){
        stringBuilder.append(ans);
    }

    public static void appendln(String ans){
        stringBuilder.append(ans).append("\n");
    }

    public static void appendError(String ans){
        stringBuilder.append("Error: ");
        stringBuilder.append(ans);
        stringBuilder.append("\n");
    }

    public static String getStringBuilder(){
        return stringBuilder.toString();
    }

    public static void clear(){
        stringBuilder.delete(0, stringBuilder.length());
    }
}
