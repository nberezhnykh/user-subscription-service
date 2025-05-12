FROM openjdk:17-jdk-slim

ARG JAR_FILE=build/libs/*.jar
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]