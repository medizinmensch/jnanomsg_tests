import nanomsg.exceptions.IOException;
import nanomsg.pubsub.PubSocket;
import nanomsg.pubsub.SubSocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Publisher {
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Init");
        UUID client_uuid = UUID.randomUUID();
        String metadata = "Client(" + client_uuid.toString().substring(0, 3) + "): ";

        PubSocket pubSocket = new PubSocket();
        pubSocket.connect("tcp://localhost:10101");

//        SubSocket subSocket = new SubSocket();
//        subSocket.connect(args[1]);
//        subSocket.subscribe("/");

        System.out.println(metadata + "Starting loop");

        while (true) {
            Thread.sleep(200);
            pubSocket.send("/ c:" + metadata + ";time:" + dtf.format(LocalDateTime.now()));
            System.out.println("msg send");
//            try {
//                String receivedData = subSocket.recvString();
//                System.out.println(metadata + "Answer: " + receivedData);
//            } catch (IOException iox) {
//                System.err.println(metadata + "nothing received");
//            }

        }
    }
}