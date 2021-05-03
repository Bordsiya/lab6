package utils;

import answers.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

/**
 * Class for receive responses
 * @author NastyaBordun
 * @version 1.1
 */
public class Receiver{
    private DatagramSocket socket;
    private byte[] bytes = new byte[16384];

    public Receiver(DatagramSocket datagramSocket){
        this.socket = datagramSocket;
    }

    public Response getResponse() {
        try {
            int attempts = 0;
            while (true){
                attempts++;
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                System.out.println("Жду ответа");
                socket.setSoTimeout(10000);
                try {
                    socket.receive(datagramPacket);
                    break;
                }
                catch (SocketTimeoutException e){
                    Printer.printError("Сервер в данный момент недоступен или занят. Подождите. [" + (5-attempts) + "]");
                    if(attempts == 5){
                        Printer.println("Сервер недоступен");
                        return null;
                    }
                }
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Response serverAnswer = (Response)objectInputStream.readObject();
            Printer.println("Ответ получен от сервера");
            return serverAnswer;
        }
        catch (IOException | ClassNotFoundException e) {
            Printer.printError("Ошибка при получении данных.");
            return null;
        }

    }
}
