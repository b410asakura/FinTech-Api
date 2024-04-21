FROM maven:3.9.5-amazoncorretto-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/FinTech-Api-0.0.1-SNAPSHOT.jar FinTech-Api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "FinTech-Api.jar"]