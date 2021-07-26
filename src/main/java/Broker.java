import nanomsg.Device;
import nanomsg.Nanomsg;
import nanomsg.pubsub.PubSocket;
import nanomsg.pubsub.SubSocket;

public class Broker {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Device: Init");
        SubSocket s1 = new SubSocket(Nanomsg.Domain.AF_SP_RAW);
        s1.bind("tcp://*:10101");
//        s1.subscribe("/");
        PubSocket s2 = new PubSocket(Nanomsg.Domain.AF_SP_RAW);
        s2.bind("tcp://*:10102");

        nanomsg.Device myDevice = new nanomsg.Device(s1.getFd(), s2.getFd());

        System.out.println("Device: Start");
        Thread deviceThread = new Thread(myDevice);
        deviceThread.start();

        System.out.println("Device: Entering the deep slumber...");
        try {
            Thread.sleep(1200000);
        } catch (InterruptedException e) {
        }

        System.out.println("Device: Interrupt");
        deviceThread.interrupt();

    }
}
