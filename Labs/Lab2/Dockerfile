FROM maven:3.8.4-openjdk-19 as build

COPY . /app

WORKDIR /app

RUN mvn clean package

FROM openjdk:19-jdk-alpine

COPY --from=build /app/target/*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]