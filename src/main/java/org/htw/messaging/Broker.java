package org.htw.messaging;

import nanomsg.Device;
import nanomsg.Nanomsg;
import nanomsg.pubsub.PubSocket;
import nanomsg.pubsub.SubSocket;

public class Broker {

    public static void main(String[] args) {
        startBroker("10101", "10102");
    }

    public static void startBroker(String subPort, String pubPort) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("Device: Init");
        SubSocket s1 = new SubSocket(Nanomsg.constants.AF_SP_RAW);
        s1.bind("tcp://*:" + subPort);
        s1.subscribe("");
        PubSocket s2 = new PubSocket(Nanomsg.constants.AF_SP_RAW);
        s2.bind("tcp://*:" + pubPort);

        System.out.println(String.format("Broker: Relaying from %s to %s", subPort, pubPort));
        Device myDevice = new nanomsg.Device(s1.getNativeSocket(), s2.getNativeSocket());

        System.out.println("Broker: Starting device");
        Thread deviceThread = new Thread(myDevice);

        System.out.println("Broker: Entering the deep slumber...");
        deviceThread.start();

        System.out.println("Device: Interrupt");
        deviceThread.interrupt();
    }
}
