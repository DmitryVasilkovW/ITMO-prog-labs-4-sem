FROM maven:3.8.4-openjdk-17

COPY . /app

WORKDIR /app

RUN mvn clean package

FROM openjdk:17-jdk-alpine

COPY --from=0 /app/target/Lab1-1.0-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]
