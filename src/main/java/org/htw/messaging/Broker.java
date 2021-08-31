package org.htw.messaging;

import nanomsg.Device;
import nanomsg.Nanomsg;
import nanomsg.pubsub.PubSocket;
import nanomsg.pubsub.SubSocket;

public class Broker {

    public static void main(String[] args) {
        startBroker();
    }

    public static void startBroker() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("Device: Init");
        SubSocket s1 = new SubSocket(Nanomsg.constants.AF_SP_RAW);
        s1.bind("tcp://*:10101");
        s1.subscribe("");
        PubSocket s2 = new PubSocket(Nanomsg.constants.AF_SP_RAW);
        s2.bind("tcp://*:10102");

        Device myDevice = new nanomsg.Device(s1.getNativeSocket(), s2.getNativeSocket());

        System.out.println("Device: Start");
        Thread deviceThread = new Thread(myDevice);

        System.out.println("Device: Entering the deep slumber...");
        deviceThread.start();

        System.out.println("Device: Interrupt");
        deviceThread.interrupt();
    }
}
