FROM openjdk:8u151-jdk-slim-stretch
ADD target/book-ui1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Xmx160m", "-jar", "/app.jar"]
EXPOSE 20192