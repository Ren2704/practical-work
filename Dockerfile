FROM openjdk:26-ea-17-jdk-slim
LABEL authors="Рената"
WORKDIR /app
COPY /build/libs/demo-0.0.1-SNAPSHOT.jar /app/demo.jar
ENTRYPOINT ["java", "-jar", "demo.jar"]