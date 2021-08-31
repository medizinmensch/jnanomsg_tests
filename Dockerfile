# syntax=docker/dockerfile:1
FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /app

COPY pom.xml .
#RUN mvn -B dependency:resolve-plugins dependency:resolve dependency:go-offline

COPY src ./src
RUN mvn package

FROM openjdk:11.0.11-jre-slim AS base
WORKDIR /app
RUN apt update
RUN apt install -y nanomsg-utils


# Subscriber
FROM base AS subscriber
COPY --from=build /app/target/subscriber-jar-with-dependencies.jar .
ENTRYPOINT ["java","-jar","subscriber-jar-with-dependencies.jar"]


# Publisher
FROM base AS publisher
WORKDIR /app

COPY --from=build /app/target/publisher-jar-with-dependencies.jar .
ENTRYPOINT ["java","-jar","publisher-jar-with-dependencies.jar"]


# Broker
FROM base AS broker
WORKDIR /app

COPY --from=build /app/target/broker-jar-with-dependencies.jar .
ENTRYPOINT ["java","-jar","broker-jar-with-dependencies.jar"]
