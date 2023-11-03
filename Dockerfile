FROM openjdk:17

MAINTAINER jodos.com

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
