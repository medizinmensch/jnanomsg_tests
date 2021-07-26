# Jnanomsg device demonstration

This is a jnanomsg device demonstration as the offical docs lack this information.

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

(Make sure to start this last!)
Broker (for Branch `broker-device`)
```
java -Dfile.encoding=UTF-8 -classpath ./target/classes:/home/$USER/.m2/repository/jnanomsg/jnanomsg/0.4.3/jnanomsg-0.4.3.jar:/home/$USER/.m2/repository/net/java/dev/jna/jna/4.2.1/jna-4.2.1.jar Broker
```