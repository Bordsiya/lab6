package utils;

import java.nio.channels.DatagramChannel;

/**
 * Less detailed pair
 * @author NastyaBordun
 * @version 1.1
 */
public class Pair {
    private ConParamSession key;
    private DatagramChannel value;

    public Pair(ConParamSession key, DatagramChannel value){
        this.key = key;
        this.value = value;
    }

    public ConParamSession getKey(){
        return this.key;
    }

    public DatagramChannel getValue(){
        return this.value;
    }
}
