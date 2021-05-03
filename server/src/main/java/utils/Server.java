package utils;

import answers.Request;
import answers.Response;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.*;
import java.util.logging.Level;

/**
 * Server
 * @author NastyaBordun
 * @version 1.1
 */
public class Server{
    /**
     * For making response
     */
    private MessageAnalyzator messageAnalyzator;
    /**
     * For creating sessions with clients
     */
    private Queue<Pair> session;
    /**
     * Clients and their requests
     */
    private HashMap<String, Queue<Request>> requests;
    /**
     * For special command for server (save)
     */
    private CollectionManager collectionManager;

    private static final int BUF_SZ = 16384;

    public Server(MessageAnalyzator messageAnalyzator, CollectionManager collectionManager){
        this.messageAnalyzator = messageAnalyzator;
        session = new LinkedList<>();
        this.requests = new HashMap<>();
        this.collectionManager = collectionManager;
    }

    /**
     * Server running
     */
    public void run() {
        try {
            Selector selector = Selector.open();
            DatagramChannel channel = DatagramChannel.open();
            InetSocketAddress isa = new InetSocketAddress(ServerLauncher.PORT);
            channel.socket().bind(isa);
            channel.configureBlocking(false);
            SelectionKey clientKey = channel.register(selector, SelectionKey.OP_READ);
            clientKey.attach(new ConParam(BUF_SZ));

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
                        ServerLauncher.logger.log(Level.INFO, "Найден запрос на чтение");
                        getRequest(key);
                    }
                    keys.remove();
                }
                while(handleRequests()){

                }
            }

        }
        catch (IOException e) {
            ServerLauncher.logger.log(Level.SEVERE, "Сервер упал");
            try {
                saveData();
            } catch (IOException ioException) {
                ServerLauncher.logger.log(Level.SEVERE, "Коллекция не сохранена в файл");
            }
        }
    }

    /**
     * For finding equals session for address
     * @param address IP address of client
     * @return result of comparison
     */
    private boolean findSession(String address){
        for(Pair data: session){
            if(data.getKey().sa.toString().equals(address)) return true;
        }
        return false;
    }

    /**
     * For debugging (Sessions)
     */
    private void printSessions(){
        System.out.println("/Все сессии->/");
        for(Pair data : session){
            System.out.println("Адрес: " + data.getKey().sa + " Канал: " + data.getValue().toString());
        }
    }

    /**
     * For debugging (requests)
     */
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

    /**
     * Adding session and request
     * @param key
     */
    private void getRequest(SelectionKey key) {
        try {
            DatagramChannel chan = (DatagramChannel)key.channel();
            ConParam con = (ConParam) key.attachment();
            con.sa = chan.receive(con.req);
            con.req.flip();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(con.req.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Request request = (Request)objectInputStream.readObject();

            ServerLauncher.logger.log(Level.INFO, "Сообщение получено");

            if((session.isEmpty() || !findSession(con.sa.toString())) && request!=null){
                ConParamSession conParamSession = new ConParamSession(BUF_SZ);
                conParamSession.sa = con.sa;
                Pair pair = new Pair(conParamSession, chan);
                session.add(pair);
                Queue<Request> queue = new LinkedList<>();
                queue.add(request);
                requests.put(con.sa.toString(), queue);
            }
            else if(request !=null && findSession(con.sa.toString())){
                Queue<Request> queue = requests.get(con.sa.toString());
                queue.add(request);
                requests.put(con.sa.toString(), queue);
            }

            objectInputStream.close();
            byteArrayInputStream.close();
            con.req.clear();

        } catch (IOException | ClassNotFoundException e) {
            ServerLauncher.logger.log(Level.SEVERE, "Ошибка во время приема запроса");
        }

    }

    /**
     * Sending request to client
     * @param conParamSession for buffer
     * @param channel for channel
     * @param request request
     * @return result
     */
    private boolean sendResponse(ConParamSession conParamSession, DatagramChannel channel, Request request){
        try{
            Response response = messageAnalyzator.handle(request);

            ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(byteArrayOutputStream1);
            objectOutputStream1.writeObject(response);
            conParamSession.resp.put(byteArrayOutputStream1.toByteArray());
            objectOutputStream1.flush();
            byteArrayOutputStream1.flush();
            conParamSession.resp.flip();
            channel.send(conParamSession.resp, conParamSession.sa);

            if(request.getCommandName().equals("exit")){
                session.remove();
                requests.remove(conParamSession.sa.toString());
            }

            conParamSession.resp.clear();
            objectOutputStream1.close();
            byteArrayOutputStream1.close();
            ServerLauncher.logger.log(Level.INFO, "Ответ отправлен");
            return true;
        }
        catch (IOException e){
            ServerLauncher.logger.log(Level.SEVERE, "Ошибка при отправке ответа");
            return false;
        }
    }

    /**
     * Dealing with all requests of user (first in {@link Server#session})
     * @return false - if we have no requests for this user
     */
    private boolean handleRequests(){
        boolean flag = false;

        if(session.isEmpty()){
            requests = new HashMap<>();
            return false;
        }

        Pair mainPair = session.element();
        String mainAddress = mainPair.getKey().sa.toString();
        Queue<Request> mainRequests = requests.get(mainAddress);

        for(Request i: mainRequests){
            if(!sendResponse(mainPair.getKey(), mainPair.getValue(), i)){
                ResponseBuilder.appendError("Ошибка при отправке ответа");
            }
            flag = true;
        }

        mainRequests = new LinkedList<>();
        requests.put(mainAddress, mainRequests);
        return flag;
    }

    /**
     * Special command for server to save collection to file
     * @throws IOException Something with I/O
     */
    private void saveData() throws IOException {
        collectionManager.saveCollection();
        ServerLauncher.logger.log(Level.INFO, "Коллекция сохранена в файл");
    }



}
