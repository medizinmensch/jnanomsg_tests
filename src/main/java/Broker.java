import nanomsg.Device;
import nanomsg.Nanomsg;
import nanomsg.Socket;
import nanomsg.reqrep.RepSocket;
import nanomsg.reqrep.ReqSocket;

public class Broker {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Device: Init");
        RepSocket s1 = new RepSocket(Nanomsg.Domain.AF_SP_RAW);
        s1.bind("tcp://*:6791");
        ReqSocket s2 = new ReqSocket(Nanomsg.Domain.AF_SP_RAW);
        s2.bind("tcp://*:6790");

        Device myDevice = new Device(s1.getFd(), s2.getFd());

        System.out.println("Device: Start");
        Thread deviceThread = new Thread(myDevice);
        deviceThread.start();

        try {
            Thread.sleep(1200000);
        } catch (InterruptedException e) {
        }

        System.out.println("Device: Interrupt");
        deviceThread.interrupt();

    }
}
