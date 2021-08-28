package msg;

import nanomsg.exceptions.IOException;
import nanomsg.pubsub.SubSocket;

public class Subscriber {
    public static void main(String[] args) throws InterruptedException {
        // Init
        System.out.println("Server: Init");

        // Wait a moment for the Broker
//        Thread.sleep(2000);

        //Init Sub Socket
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