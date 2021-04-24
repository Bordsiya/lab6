package utils;

import answers.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;


public class Receiver{
    private DatagramChannel datagramChannel;
    private SocketAddress client;
    private ByteBuffer recieveBuffer = ByteBuffer.allocate(16384);

    public Receiver(DatagramChannel datagramChannel, SocketAddress client){
        this.datagramChannel = datagramChannel;
        this.client = client;
    }

    public Request getRequest() {
        try {
            datagramChannel.configureBlocking(false);
            datagramChannel.bind(client);
            datagramChannel.receive(recieveBuffer);
            recieveBuffer.flip();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(recieveBuffer.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Request request = (Request) objectInputStream.readObject();
            ResponseBuilder.appendln("Сообщение получено");
            objectInputStream.close();
            byteArrayInputStream.close();
            recieveBuffer.clear();
            datagramChannel.disconnect();
            return request;

        } catch (IOException e) {
            ResponseBuilder.appendError("Ошибка ввода/вывода");
            return null;
        }
        catch (ClassCastException | ClassNotFoundException e){
            ResponseBuilder.appendError("Некорректный запрос");
            return null;
        }
    }
}
