import nanomsg.reqrep.RepSocket;

public class Server {
    public static void main(String[] args) {
        RepSocket sock = new RepSocket();
        sock.bind("tcp://*:6789");

        while (true) {
            byte[] receivedData = sock.recvBytes();
            sock.send(receivedData);
        }
    }
}