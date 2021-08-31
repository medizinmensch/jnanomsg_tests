package org.htw.messaging;

import org.apache.commons.cli.*;

public class Service {
    public static void main(String[] args) {

        Options options = getCliOptions();
        CommandLineParser parser = new GnuParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("help")) {
                printHelp(options);
                System.exit(0);
            }

            if (cmd.hasOption("broker")) {
                if (!cmd.hasOption("subscribe-port") | !cmd.hasOption("publish-port")) {
                    System.out.println("CLI Error: Missing option <subport> or <pubport>");
                    printHelp(options);
                    System.exit(2);
                }

                Broker.startBroker(cmd.getOptionValue("subscribe-port"), cmd.getOptionValue("publish-port"));
            } else if (cmd.hasOption("node")) {
                if (!cmd.hasOption("subscribe-uri") | !cmd.hasOption("publish-uri")) {
                    System.out.println("CLI Error: Missing option <subscribe-uri> or <publish-uri>");
                    printHelp(options);
                    System.exit(2);
                }

                Node.sendBeacon(cmd.getOptionValue("subscribe-uri"), cmd.getOptionValue("publish-uri"));
            } else {
                printHelp(options);
                System.exit(2);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("jnanomsg-service", options);
    }

    public static Options getCliOptions() {
        Options options = new Options();

        options.addOption("n", "node", false,
                "Starts a node which is sending beacons every second.");
        options.addOption("b", "broker", false,
                "Starts a broker to relay messages.");
        options.addOption("sp", "subscribe-port", true, "Port to relay from (broker)");
        options.addOption("pp", "publish-port", true, "Port to relay to (broker)");
        options.addOption("su", "subscribe-uri", true, "URI to subscribe to (node)");
        options.addOption("pu", "publish-uri", true, "URI to publish to (node)");
        options.addOption("h", "help", false, "Print this message");

        return options;

    }
}
