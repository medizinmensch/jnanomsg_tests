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
        System.out.println("Broker: Init");
        SubSocket s1 = new SubSocket(Nanomsg.constants.AF_SP_RAW);
        s1.bind("tcp://*:" + subPort);
        s1.subscribe("");
        PubSocket s2 = new PubSocket(Nanomsg.constants.AF_SP_RAW);
        s2.bind("tcp://*:" + pubPort);

        System.out.println(String.format("Broker: Relaying from %s to %s", subPort, pubPort));
        Device myDevice = new nanomsg.Device(s1.getNativeSocket(), s2.getNativeSocket());

        Thread deviceThread = new Thread(myDevice);

        while (true) {
            System.out.println("Starting new Thread.");
            deviceThread = new Thread(myDevice);
            deviceThread.start();
            while (deviceThread.isAlive()) {
                System.out.println("Old Thread is still alive...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void logThread(Thread deviceThread, String msg) {
        System.out.println(msg + " Interrupted: " + deviceThread.isInterrupted() + ", Alive: " + deviceThread.isAlive());
    }
}
