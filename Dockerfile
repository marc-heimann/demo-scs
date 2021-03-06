FROM openjdk:8-jdk-alpine

MAINTAINER Marc Heimann <marc.heimann@vanderlande.com>

VOLUME /tmp

COPY target/DemoSCS.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]