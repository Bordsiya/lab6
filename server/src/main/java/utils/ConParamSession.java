package utils;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

/**
 * Data for channel, not connected with them
 */
public class ConParamSession {
    ByteBuffer req;
    ByteBuffer resp;
    SocketAddress sa;

    public ConParamSession(int BUF_SZ) {
        req = ByteBuffer.allocate(BUF_SZ);
        resp = ByteBuffer.allocate(BUF_SZ);
    }
}
