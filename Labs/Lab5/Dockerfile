FROM gradle:7.3.3-jdk11 as build

COPY . /app

WORKDIR /app

RUN gradle clean build

FROM amazoncorretto:21

RUN java -version

COPY --from=build /app/build/libs/*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]
