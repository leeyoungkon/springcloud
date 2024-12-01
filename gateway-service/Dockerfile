FROM openjdk:8u151-jdk-slim-stretch
ADD target/gateway-service1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Xmx144m", "-jar", "/app.jar"]
EXPOSE 20193