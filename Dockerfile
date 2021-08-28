FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
# ENTRYPOINT ["sleep", "1000"]
RUN mvn install
RUN mvn compile
RUN mvn package

# Subscriber
FROM openjdk:11-jre-slim AS subscriber
COPY --from=build /home/app/target/subscriber-jar-with-dependencies.jar /usr/local/lib/subscriber.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/subscriber.jar"]


# Publisher
FROM openjdk:11-jre-slim AS publisher
COPY --from=build /home/app/target/publisher-jar-with-dependencies.jar /usr/local/lib/publisher.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/publisher.jar"]


# Broker
FROM openjdk:11-jre-slim AS broker
COPY --from=build /home/app/target/broker-jar-with-dependencies.jar /usr/local/lib/broker.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/broker.jar"]
