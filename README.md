# Jnanomsg tests

To make an issue reproducible, I created this issue with a minimal jnanomsg setup.

# Build

```
mvn compile
```

# Run

You might have to adapt that. 

Publisher:
```
java -Dfile.encoding=UTF-8 -classpath ./target/classes:/home/$USER/.m2/repository/jnanomsg/jnanomsg/0.4.3/jnanomsg-0.4.3.jar:/home/$USER/.m2/repository/net/java/dev/jna/jna/4.2.1/jna-4.2.1.jar Publisher
```

Subscriber:
```
java -Dfile.encoding=UTF-8 -classpath ./target/classes:/home/$USER/.m2/repository/jnanomsg/jnanomsg/0.4.3/jnanomsg-0.4.3.jar:/home/$USER/.m2/repository/net/java/dev/jna/jna/4.2.1/jna-4.2.1.jar Subscriber
```

Broker (for Branch `broker-device`)
```
java -Dfile.encoding=UTF-8 -classpath ./target/classes:/home/$USER/.m2/repository/jnanomsg/jnanomsg/0.4.3/jnanomsg-0.4.3.jar:/home/$USER/.m2/repository/net/java/dev/jna/jna/4.2.1/jna-4.2.1.jar Broker
```