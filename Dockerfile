# Dockerfile
FROM openjdk:17

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./.src
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "LandmarkAPI_Security.jar"]
RUN ./mvnw dependency:go-offline

CMD ["./mvnw", "spring-boot:run"]

