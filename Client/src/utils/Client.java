package utils;

import answers.Request;
import answers.Response;
import exceptions.IncorrectCommandException;
import utils.Business;
import utils.Printer;
import utils.Receiver;
import utils.Sender;

import java.io.IOException;
import java.net.*;

public class Client {
    private final InetAddress host;
    private final int port;
    private Receiver receiver;
    private Sender sender;
    private Business business;

    public Client(InetAddress host, int port, Receiver receiver, Sender sender, Business business){
        this.host = host;
        this.port = port;
    }

    public void handle(String command){
        try {
            Request request = business.makeRequest(command);
            if(request != null){
                sender.send(request, port);
                Response response = receiver.getResponse();
                Printer.print(response.getResponseBody());
            }

        } catch (IncorrectCommandException e) {
            e.printStackTrace();
        }
    }


}
