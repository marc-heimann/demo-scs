FROM openjdk:8-jdk-alpine
MAINTAINER Marc Heimann <marc.heimann@swisslog.com>
VOLUME /tmp
ARG JAR_FILE
COPY target/SwisslogDemoSCS.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]