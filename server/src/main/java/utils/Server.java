package utils;

import answers.Request;
import answers.Response;
import javafx.util.Pair;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.*;

public class Server{
    private MessageAnalyzator messageAnalyzator;
    private int port;
    private Queue<Pair<ConParamSession, DatagramChannel>> session;
    private HashMap<String, Queue<Request>> requests;

    public Server(MessageAnalyzator messageAnalyzator, int port){
        this.messageAnalyzator = messageAnalyzator;
        this.port = port;
        session = new LinkedList<>();
        this.requests = new HashMap<>();
    }

    public void run() {
        Request request = null;
        try {
            Selector selector = Selector.open();
            DatagramChannel channel = DatagramChannel.open();
            InetSocketAddress isa = new InetSocketAddress(port);
            channel.socket().bind(isa);
            channel.configureBlocking(false);
            SelectionKey clientKey = channel.register(selector, SelectionKey.OP_READ);
            clientKey.attach(new ConParam(16384));

            System.out.println("Сервер запустился");

            while(true){
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keys = selectedKeys.iterator();
                while(keys.hasNext()){
                    SelectionKey key = keys.next();
                    if (!key.isValid()) {
                        continue;
                    }
                    if(key.isReadable()){
                        System.out.println("Нашел запрос на чтение!");
                        request = getRequest(key);
                        /*
                        if(request != null) {
                            Response response = messageAnalyzator.handle(request);
                            System.out.println(response.toString());
                            //key.interestOps(SelectionKey.OP_WRITE);
                        }

                         */
                    }
                    /*
                    else if(key.isWritable()){
                        System.out.println("Нашел запрос на запись!");
                        if(!sendResponse(key, request)){
                            ResponseBuilder.appendError("Ошибка при отправке ответа");
                        }
                        key.interestOps(SelectionKey.OP_READ);
                    }

                     */
                    keys.remove();
                }
                //System.out.println("---Пришли в handleRequest---");
                while(handleRequests()){

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean findSession(String address){
        for(Pair<ConParamSession, DatagramChannel> data: session){
            if(data.getKey().sa.toString().equals(address)) return true;
        }
        return false;
    }

    private void printSessions(){
        System.out.println("/Все сессии->/");
        for(Pair<ConParamSession, DatagramChannel> data : session){
            System.out.println("Адрес: " + data.getKey().sa + " Канал: " + data.getValue().toString());
        }
    }

    private void printRequests(){
        System.out.println("/Все запросы->/");
        for(Map.Entry<String, Queue<Request>> pair : requests.entrySet()) {
            System.out.println("Адрес: " + pair.getKey());
            for (Request i: pair.getValue()){
                System.out.println("Запрос: " + i.getCommandName());
            }
            System.out.println("/////");
        }
    }

    private Request getRequest(SelectionKey key) {
        try {
            //System.out.println("---Зашли в getRequest");
            DatagramChannel chan = (DatagramChannel)key.channel();
            ConParam con = (ConParam) key.attachment();
            con.sa = chan.receive(con.req);
            con.req.flip();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(con.req.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Request request = (Request)objectInputStream.readObject();
            //System.out.println("-----Сообщение получено: \n" + request);
            //System.out.println("-----Адрес: " + con.sa);
            //System.out.println("-----До-----");
            //printSessions();
            //printRequests();
            if((session.isEmpty() || !findSession(con.sa.toString())) && request!=null){
                //новый адрес
                //System.out.println("Зашли в новый адрес->");
                ConParamSession conParamSession = new ConParamSession(16384);
                conParamSession.sa = con.sa;
                Pair<ConParamSession, DatagramChannel> pair = new Pair<>(conParamSession, chan);
                session.add(pair);
                Queue<Request> queue = new LinkedList<>();
                queue.add(request);
                requests.put(con.sa.toString(), queue);
                System.out.println(request.toString());
            }
            else if(request !=null && findSession(con.sa.toString())){
                //добавляем новый запрос на старый адрес
                //System.out.println("Зашли в старый адрес->");
                Queue<Request> queue = requests.get(con.sa.toString());
                queue.add(request);
                requests.put(con.sa.toString(), queue);
            }
            //System.out.println("-----После-----");
            //printSessions();
            //printRequests();
            objectInputStream.close();
            byteArrayInputStream.close();
            con.req.clear();
            return request;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    private boolean sendResponse(ConParamSession conParamSession, DatagramChannel channel, Request request){
        try{
            //System.out.println("Мы в sendResponse");
            Response response = messageAnalyzator.handle(request);
            System.out.println("Ответ: " + response);
            ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(byteArrayOutputStream1);
            objectOutputStream1.writeObject(response);
            conParamSession.resp.put(byteArrayOutputStream1.toByteArray());
            objectOutputStream1.flush();
            byteArrayOutputStream1.flush();
            conParamSession.resp.flip();
            channel.send(conParamSession.resp, conParamSession.sa);
            if(request.getCommandName().equals("exit")){
                //System.out.println("Мы зашли в exit");
                session.remove();
                requests.remove(conParamSession.sa.toString());
            }
            //printSessions();
            //printRequests();
            conParamSession.resp.clear();
            objectOutputStream1.close();
            byteArrayOutputStream1.close();
            System.out.println("Ответ отправлен");
            return true;
        }
        catch (IOException e){
            System.out.println("Ответ не отправлен");
            return false;
        }
    }

    private boolean handleRequests(){
        //System.out.println("Мы в handleRequests");
        boolean flag = false;
       // printSessions();
        //printRequests();
        if(session.isEmpty()){
            requests = new HashMap<>();
            return false;
        }
        Pair<ConParamSession, DatagramChannel> mainPair = session.element();
        String mainAdress = mainPair.getKey().sa.toString();
        Queue<Request> mainRequests = requests.get(mainAdress);
        for(Request i: mainRequests){
            if(!sendResponse(mainPair.getKey(), mainPair.getValue(), i)){
                ResponseBuilder.appendError("Ошибка при отправке ответа");
            }
            flag = true;
        }
        mainRequests = new LinkedList<>();
        requests.put(mainAdress, mainRequests);
        //System.out.println("Мы выходим из handleRequest");
        //printSessions();
        //printRequests();
        return flag;
    }



}
