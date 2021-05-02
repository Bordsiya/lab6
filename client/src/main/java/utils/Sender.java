package utils;

import answers.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender{
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] bytes = new byte[16384];

    public Sender(DatagramSocket datagramSocket, InetAddress inetAddress){
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public boolean send(Request clientAnswer, int port){
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)){
            objectOutputStream.writeObject(clientAnswer);
            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, inetAddress, port);
            datagramSocket.send(datagramPacket);
            Printer.println("Пакет отправлен");
            return true;
        } catch (IOException e) {
            Printer.printError("Ошибка ввода/вывода");
            return false;
        }
    }
}
