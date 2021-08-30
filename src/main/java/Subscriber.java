package msg;

import nanomsg.exceptions.IOException;
import nanomsg.pubsub.SubSocket;

public class Subscriber {
    public static void main(String[] args) throws InterruptedException {
        // Init
        System.out.println("Server: Init");

        //Init Sub Socket
        SubSocket subSocket = new SubSocket();
        subSocket.connect("tcp://broker:10102");
        subSocket.subscribe("/");


        while (true) {
            try {
                String receivedData = subSocket.recvString();
                System.out.println("Received: <" + receivedData + ">");
            } catch (IOException iox) {
                System.err.println("Server: nothing received");
            }
        }
    }
}