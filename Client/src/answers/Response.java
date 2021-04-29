package answers;

import java.io.Serializable;

public class Response implements Serializable {
    private ResponseAnswer responseAnswer;
    private String ResponseBody;

    public Response(ResponseAnswer responseAnswer, String responseBody){
        this.responseAnswer = responseAnswer;
        this.ResponseBody = responseBody;
    }

    public ResponseAnswer getResponseAnswer() {
        return responseAnswer;
    }

    public String getResponseBody() {
        return ResponseBody;
    }

    @Override
    public String toString() {
        return "Ответ:\n" + "Код ответа: " + this.getResponseAnswer() + "\nТекст ответа: " + this.ResponseBody + "\n";
    }
}
