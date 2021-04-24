package utils;

import answers.Response;

import java.io.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Sender{
    private DatagramChannel datagramChannel;
    private SocketAddress client;
    private ByteBuffer sendBuffer = ByteBuffer.allocate(16384);

    public Sender(DatagramChannel datagramChannel, SocketAddress client){
        this.datagramChannel = datagramChannel;
        this.client = client;
    }

    public boolean send(Response serverAnswer){
        try {
            datagramChannel.configureBlocking(false);
            datagramChannel.bind(client);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serverAnswer);
            sendBuffer.put(byteArrayOutputStream.toByteArray());
            objectOutputStream.flush();
            byteArrayOutputStream.flush();
            sendBuffer.flip();
            datagramChannel.send(sendBuffer, client);
            //("-----/nСообщение отправлено/n-----");
            objectOutputStream.close();
            byteArrayOutputStream.close();
            datagramChannel.disconnect();
            sendBuffer.clear();
            return true;

        } catch (IOException e) {
            ResponseBuilder.appendError("Ошибка ввода/вывода");
            return false;
        }
    }


}
