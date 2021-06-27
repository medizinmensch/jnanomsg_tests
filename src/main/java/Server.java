import nanomsg.exceptions.IOException;
import nanomsg.reqrep.RepSocket;

public class Server {
    public static void main(String[] args) {
        RepSocket sock = new RepSocket();
        sock.bind("tcp://*:6789");

        while (true) {
            try {
                byte[] receivedData = sock.recvBytes();
                sock.send(receivedData);
            } catch (IOException iox) {
                System.err.println("nothing received");
            }
        }
    }
}