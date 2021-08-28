package msg;

import nanomsg.pubsub.PubSocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Publisher {
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        // Init
        System.out.println("Init");
        UUID client_uuid = UUID.randomUUID();
        String metadata = "Client(" + client_uuid.toString().substring(0, 3) + "): ";

        // Init Publish Socket
        PubSocket pubSocket = new PubSocket();
        pubSocket.connect("tcp://localhost:10101");

        while (true) {
            Thread.sleep(200);
            String msg = "/ c:" + metadata + ";time:" + dtf.format(LocalDateTime.now());
            pubSocket.send(msg);
            System.out.println("msg send: " + msg);
        }
    }
}