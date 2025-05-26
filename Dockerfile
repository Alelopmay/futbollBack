
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /app/target/equipfix-1.0-SNAPSHOT.jar /app/equipfix.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/equipfix.jar"]
