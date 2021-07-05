import nanomsg.exceptions.IOException;
import nanomsg.reqrep.RepSocket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Server: Init");
        RepSocket sock = new RepSocket();
        sock.connect("tcp://127.0.0.1:6790");

        while (true) {
            try {
                String receivedData = sock.recvString();
                System.out.println("Received:" + receivedData);
                sock.send(receivedData);
            } catch (IOException iox) {
                System.err.println("Server: nothing received");
            }
        }
    }
}