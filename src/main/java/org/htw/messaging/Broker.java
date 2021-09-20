package org.htw.messaging;

import nanomsg.Device;
import nanomsg.Nanomsg;
import nanomsg.pubsub.PubSocket;
import nanomsg.pubsub.SubSocket;

import static org.htw.utils.Utils.sleep;

public class Broker {

    public static void main(String[] args) {
        startBroker("10101", "10102");
    }

    public static void startBroker(String subPort, String pubPort) {
        System.out.println("Broker: Init");
        SubSocket s1 = new SubSocket(Nanomsg.constants.AF_SP_RAW);
        s1.bind("tcp://*:" + subPort);
//        s1.bind("ipc:///tmp/jnanomsg_tests" + subPort);
        s1.subscribe("");
        PubSocket s2 = new PubSocket(Nanomsg.constants.AF_SP_RAW);
        s2.bind("tcp://*:" + pubPort);
//        s2.bind("ipc:///tmp/jnanomsg_tests" + pubPort);

        System.out.println(String.format("Broker: Relaying from port %s to %s", subPort, pubPort));
        Device myDevice = new nanomsg.Device(s1.getNativeSocket(), s2.getNativeSocket());

        Thread deviceThread = new Thread(myDevice);

        System.out.println("Starting a new thread.");
        while (true) {
            deviceThread = new Thread(myDevice);
            deviceThread.start();
//            sleep(30000);
            while (deviceThread.isAlive()) {
                sleep(2000);
            }
            System.out.println("Old Thread died. Starting a new one.");
        }
    }

//    public static void sleep(long ms) {
//        try {
//            Thread.sleep(ms);
//        } catch (InterruptedException e) {
//        }
//    }
}
