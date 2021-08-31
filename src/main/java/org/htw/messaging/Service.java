package org.htw.messaging;

import org.apache.commons.cli.*;

public class Service {
    public static void main(String[] args) {
        // create Options object
        Options options = new Options();

        // add t option
        options.addOption("n", false, "Start node");
        options.addOption("b", false, "Start broker");

        CommandLineParser parser = new GnuParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("n")) {
                Node n1 = new Node();
                n1.sendBeacon();
            }
            if (cmd.hasOption("b")){
                Broker.startBroker();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
