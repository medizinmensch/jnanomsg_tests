import nanomsg.exceptions.IOException;
import nanomsg.pubsub.PubSocket;
import nanomsg.pubsub.SubSocket;

public class Subscriber {
    public static void main(String[] args) {
        System.out.println("Server: Init");

        SubSocket subSocket = new SubSocket();
        subSocket.connect("tcp://localhost:10102");
        subSocket.subscribe("/");


        while (true) {
            try {
                String receivedData = subSocket.recvString();
                System.out.println("Received:" + receivedData);
//                pubSocket.send(receivedData);
            } catch (IOException iox) {
                System.err.println("Server: nothing received");
            }
        }
    }
}