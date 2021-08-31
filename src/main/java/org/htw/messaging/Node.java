package org.htw.messaging;

import nanomsg.exceptions.IOException;
import nanomsg.pubsub.PubSocket;
import nanomsg.pubsub.SubSocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Node {
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static UUID client_uuid = UUID.randomUUID();

    public static void main(String[] args) {

    }

    public void sendBeacon() {
        // Init
        System.out.println("Init");
        String metadata = "Client(" + client_uuid.toString().substring(0, 3) + ")";

        // Init Pub Socket
        PubSocket pubSocket = new PubSocket();
        pubSocket.connect("tcp://broker:10101");

        //Init Sub Socket
        SubSocket subSocket = new SubSocket();
        subSocket.connect("tcp://broker:10102");
        subSocket.subscribe("/");

        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }

            String msg = "/ c:" + metadata + ";time:" + dtf.format(LocalDateTime.now());
            pubSocket.send(msg);
            System.out.println("msg send: <" + msg + ">");

            try {
                String receivedData = subSocket.recvString();
                System.out.println("Received: <" + receivedData + ">");
            } catch (IOException iox) {
                System.err.println("Server: nothing received");
            }
        }
    }

}