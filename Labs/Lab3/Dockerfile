FROM maven:3.9.6-amazoncorretto-21 as build

COPY . /app

WORKDIR /app

RUN mvn clean package

FROM openjdk:21

RUN java -version

COPY --from=build /app/target/*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]