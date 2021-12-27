# stage 1 - Build
FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /build
COPY . ./
RUN mvn clean package -X

# stage 2 — Build production image
FROM openjdk:11-jdk-slim

ENV APP_HOME ./
WORKDIR APP_HOME
COPY --from=build /build/target/x-men-0.0.1-SNAPSHOT.jar APP_HOME/app.jar
ENTRYPOINT java -jar APP_HOME/app.jar
