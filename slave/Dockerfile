# 1. OpenJDK 기반 이미지 사용
FROM openjdk:17-jdk-slim

# 2. 애플리케이션 실행 경로 설정
WORKDIR /app

# 3. JAR 파일을 도커 이미지로 복사
COPY target/slave-0.0.1-SNAPSHOT.jar app.jar

# 4. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
