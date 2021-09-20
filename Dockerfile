# syntax=docker/dockerfile:1
FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:resolve-plugins dependency:resolve dependency:go-offline

COPY src ./src
RUN mvn compile assembly:single

FROM openjdk:11.0.11-jre-slim AS base
WORKDIR /app
RUN apt update
RUN apt install -y nanomsg-utils

# Node
FROM base AS node
WORKDIR /app
COPY --from=build /app/target/jnanomsg-service-jar-with-dependencies.jar .
ENTRYPOINT ["java","-jar","jnanomsg-service-jar-with-dependencies.jar", "--node","--subscribe-uri", "tcp://broker:10102", "--publish-uri", "tcp://broker:10101"]

# Broker
FROM base AS broker
WORKDIR /app
COPY --from=build /app/target/jnanomsg-service-jar-with-dependencies.jar .
ENTRYPOINT ["java","-jar","jnanomsg-service-jar-with-dependencies.jar", "--broker", "--subscribe-port", "10101", "--publish-port", "10102"]
