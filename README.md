# Jnanomsg tests

To make an issue reproducible, I created this issue with a minimal jnanomsg setup.

# Build

```
mvn compile
```

# Run

You will have to adapt that. Or you'll use IntelliJ.

```
java -Dfile.encoding=UTF-8 -classpath ./target/classes:/home/youri/.m2/repository/jnanomsg/jnanomsg/0.4.3/jnanomsg-0.4.3.jar:/home/youri/.m2/repository/net/java/dev/jna/jna/4.2.1/jna-4.2.1.jar Client
```
