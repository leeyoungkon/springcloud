FROM openjdk:8u151-jdk-slim-stretch
ADD target/fourth-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Xmx160m", "-jar", "/app.jar"]
EXPOSE 28099