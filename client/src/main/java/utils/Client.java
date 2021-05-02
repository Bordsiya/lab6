package utils;

import answers.Request;
import answers.Response;
import exceptions.IncorrectCommandException;

import java.net.InetAddress;

public class Client {
    private final InetAddress host;
    private final int port;
    private Receiver receiver;
    private Sender sender;
    private Business business;

    public Client(InetAddress host, int port, Receiver receiver, Sender sender, Business business){
        this.host = host;
        this.port = port;
        this.receiver = receiver;
        this.sender = sender;
        this.business = business;
    }

    public void handle(String command){
        try {
            Request request = business.makeRequest(command);
            if(request != null){
                if(sender.send(request, port)){
                    Printer.println(request.toString());
                    Response response = receiver.getResponse();
                    if(response != null){
                        Printer.print(response.getResponseBody());
                    }
                }
            }

        } catch (IncorrectCommandException e) {
            Printer.printError(e.getMessage());
        }
    }


}
