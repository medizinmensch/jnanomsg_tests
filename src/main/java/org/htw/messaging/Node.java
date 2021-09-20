package org.htw.messaging;

import nanomsg.exceptions.IOException;
import nanomsg.pubsub.PubSocket;
import nanomsg.pubsub.SubSocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.htw.utils.Utils.sleep;

public class Node {
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static UUID client_uuid = UUID.randomUUID();

    public static void main(String[] args) {
        sendBeacon("tcp://broker:10101", "tcp://broker:10102");
    }

    public static void sendBeacon(String subUri, String pubUri) {
        // Init
        String metadata = "Client(" + client_uuid.toString().substring(0, 3) + ")";
        System.out.println(String.format("%s: Init Client. Pub to: %s, Sub to: %s", metadata, pubUri, subUri));

        // Init Pub Socket
        PubSocket pubSocket = new PubSocket();
        pubSocket.connect(pubUri);

        //Init Sub Socket
        SubSocket subSocket = new SubSocket();
        subSocket.connect(subUri);
        subSocket.subscribe("");


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