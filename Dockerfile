FROM openjdk:17-jdk-slim
WORKDIR /firstapp
COPY target/Application-0.0.1-SNAPSHOT.jar /firstapp/Application-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "Application-0.0.1-SNAPSHOT.jar"]
