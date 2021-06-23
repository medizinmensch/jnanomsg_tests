import nanomsg.reqrep.ReqSocket;

public class Client {
    public static void main(String[] args) {
        System.out.println("Init");
        ReqSocket sock = new ReqSocket();
        sock.connect("tcp://localhost:6789");

        System.out.println("Starting loop");

        while (true) {
            System.out.println("Loop: 1");
            sock.send("Hello!" + 1);
            System.out.println("Loop: 2");
            System.out.println("Received:" + sock.recvString());
            System.out.println("Loop: 3");
        }
    }
}