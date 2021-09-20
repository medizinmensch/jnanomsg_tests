package org.htw.messaging;

import nanomsg.exceptions.IOException;
import nanomsg.pubsub.PubSocket;
import nanomsg.pubsub.SubSocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.htw.utils.Utils.sleep;

public class Node implements Runnable {
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static UUID client_uuid = UUID.randomUUID();
    public String metadata;

    public static SubSocket subSocket;
    public static PubSocket pubSocket;

    public static void main(String[] args) {
        new Node("tcp://broker:10101", "tcp://broker:10102", "1");
    }

    public Node(String subUri, String pubUri, String id) {
        // Init
        metadata = "Client(" + id + ")";
        System.out.println(String.format("%s: Init Client. Pub to: %s, Sub to: %s", metadata, pubUri, subUri));

        // Init Pub Socket
        pubSocket = new PubSocket();
        pubSocket.connect(pubUri);

        //Init Sub Socket
        subSocket = new SubSocket();
        subSocket.connect(subUri);
        subSocket.subscribe("");
    }

    @Override
    public void run() {
        while (true) {
            sleep(200);

            while (true) {
                try {
                    String receivedData = subSocket.recvString(false);
//                    String test = subSocket.re
                    System.out.println("Received: <" + receivedData + ">");
                } catch (IOException iox) {
//                    System.err.println("Received: [nothing]");
                    break;
                }
            }

            String msg = "/ c:" + metadata + ";time:" + dtf.format(LocalDateTime.now());
            pubSocket.send(msg);
            System.out.println("msg send: <" + msg + ">");
        }
    }
}