import nanomsg.exceptions.IOException;
import nanomsg.reqrep.ReqSocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Client {
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        UUID client_uuid = UUID.randomUUID();
        String metadata = "Client(" + client_uuid.toString().substring(0,3) + "): ";
        System.out.println(metadata + "Init");

        ReqSocket sock = new ReqSocket();
        sock.connect("tcp://localhost:6791");
        System.out.println(metadata + "Starting loop");

        while (true) {
            Thread.sleep(100);
            sock.send("c:" + client_uuid.toString().substring(1,10) + ";time:" + dtf.format(LocalDateTime.now()));
            try {
                String receivedData = sock.recvString();
                System.out.println(metadata + "Answer: " + receivedData);
            } catch (IOException iox) {
                System.err.println(metadata + "nothing received");
            }

        }
    }
}