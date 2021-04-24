import answers.Request;
import answers.Response;
import utils.MessageAnalyzator;
import utils.Receiver;
import utils.ResponseBuilder;
import utils.Sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server{
    private MessageAnalyzator messageAnalyzator;
    private Receiver receiver;
    private Sender sender;

    public Server(MessageAnalyzator messageAnalyzator, Receiver receiver, Sender sender){
        this.messageAnalyzator = messageAnalyzator;
        this.receiver = receiver;
        this.sender = sender;
    }

    public void run() {
        while(true){
            Request request = receiver.getRequest();
            Response response = messageAnalyzator.handle(request);
            if(!sender.send(response)){
                ResponseBuilder.appendError("Ошибка при отправке ответа");
            }
        }
    }
}
