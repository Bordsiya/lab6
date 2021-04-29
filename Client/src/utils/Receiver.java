package utils;

import answers.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver{
    private DatagramSocket datagramSocket;
    private byte[] bytes = new byte[16384];

    public Receiver(DatagramSocket datagramSocket){
        this.datagramSocket = datagramSocket;
    }

    public Response getResponse() {
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        try {
            datagramSocket.receive(datagramPacket);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Response serverAnswer = (Response)objectInputStream.readObject();
            Printer.println("Ответ получен от сервера/n");
            return serverAnswer;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
