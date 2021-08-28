#!/bin/bash

cleanup() {
  pkill -f "broker-jar-with-dependencies.*"
  pkill -f "publisher-jar-with-dependencies.*"
  pkill -f "subscriber-jar-with-dependencies.*"
  printf "\nCleanup OK\n"
#   pkill -f "jar-with-dependencies.*"
  exit 1
}

java -jar ./target/subscriber-jar-with-dependencies.jar &
java -jar ./target/publisher-jar-with-dependencies.jar &
java -jar ./target/broker-jar-with-dependencies.jar

trap cleanup INT
trap "exit 1" TERM

cleanup


# Subscriber -> Broker TCP/10101 ✅
# Subscriber -> Broker TCP/10102 ✅
# Publisher -> Broker TCP/10101 ✅
# Publisher -> Broker TCP/10102 ✅
