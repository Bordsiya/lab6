package utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ClientLauncher {

    public void launchClient(){
        BufferedInputStream bf = new BufferedInputStream(System.in);
        BufferedReader r = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
        AskManager askManager = new AskManager(r);
        Business business = new Business(askManager);
        FileManager fileManager = new FileManager();

        try {
            InetAddress ADDR = InetAddress.getByName("localhost");
            int PORT = 2;
            SocketAddress socketAddress = new InetSocketAddress(ADDR, PORT);
            DatagramSocket datagramSocket = new DatagramSocket(socketAddress);
            Receiver receiver = new Receiver(datagramSocket);
            Sender sender = new Sender(datagramSocket, ADDR);
            Client client = new Client(ADDR, PORT, receiver, sender, business);
            Console console = new Console(r, fileManager, client, askManager);
            business.addConsole(console);
            console.interactiveMode();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }

    }

}
