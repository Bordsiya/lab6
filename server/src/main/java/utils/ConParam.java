package utils;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

/**
 * Data, connected with channel
 * @author NastyaBordun
 * @version 1.1
 */
public class ConParam {
    ByteBuffer req;
    ByteBuffer resp;
    SocketAddress sa;

    public ConParam(int BUF_SZ) {
        req = ByteBuffer.allocate(BUF_SZ);
        resp = ByteBuffer.allocate(BUF_SZ);
    }
}
