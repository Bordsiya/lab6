package utils;

import answers.Request;
import answers.Response;
import exceptions.IncorrectCommandException;

import java.net.InetAddress;

/**
 * Client
 * @author NastyaBordun
 * @version 1.1
 */
public class Client {
    /**
     * Class for receiving responses
     */
    private Receiver receiver;
    /**
     * Class for sending requests
     */
    private Sender sender;
    /**
     * Class for making requests
     */
    private Business business;

    /**
     * Constructor for class
     * @param receiver {@link Receiver}
     * @param sender {@link Sender}
     * @param business {@link Business}
     */
    public Client(Receiver receiver, Sender sender, Business business){
        this.receiver = receiver;
        this.sender = sender;
        this.business = business;
    }

    /**
     * Interaction with Server
     * @param command command from Console
     */
    public void handle(String command){
        try {
            Request request = business.makeRequest(command);
            if(request != null){
                if(sender.send(request, ClientLauncher.PORT)){
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
