import nanomsg.Device;
import nanomsg.reqrep.RepSocket;
import nanomsg.reqrep.ReqSocket;

public class Broker {

    public static void main(String[] args) throws InterruptedException {

//          This is from the official nanomsg documentation (C-Implementation)
//          as a Reference: https://nanomsg.org/v1.1.5/nn_device.html
//      int s1 = nn_socket (AF_SP_RAW, NN_REQ);
//      nn_bind (s1, "tcp://*:5555");
//      int s2 = nn_socket (AF_SP_RAW, NN_REP);
//      nn_bind (s2, "tcp://*:5556");
//      nn_device (s1, s2);

        System.out.println("Device: Init");
        RepSocket s1 = new RepSocket();
        s1.bind("tcp://*:6791");
        ReqSocket s2 = new ReqSocket();
        s2.bind("tcp://*:6790");

//          I would the first one should work, but I've tried all permutations
        Device myDevice = new Device(s1.getFd(), s2.getFd());
//      Device myDevice = new Device(s1.getFd(), s2.getRcvFd());
//      Device myDevice = new Device(s1.getFd(), s2.getSndFd());
//      Device myDevice = new Device(s1.getRcvFd(), s2.getFd());
//      Device myDevice = new Device(s1.getRcvFd(), s2.getRcvFd());
//      Device myDevice = new Device(s1.getRcvFd(), s2.getSndFd());
//      Device myDevice = new Device(s1.getSndFd(), s2.getFd());
//      Device myDevice = new Device(s1.getSndFd(), s2.getRcvFd());
//      Device myDevice = new Device(s1.getSndFd(), s2.getSndFd());


        System.out.println("Device: Start");
        Thread deviceThread = new Thread(myDevice);
        deviceThread.start();

        try {
            Thread.sleep(1200000);
        } catch (InterruptedException e) {}

        System.out.println("Device: Interrupt");
        deviceThread.interrupt();

    }
}
