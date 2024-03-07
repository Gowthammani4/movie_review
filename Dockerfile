
FROM maven:3.9.6-eclipse-temurin-21-jammy
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY src /app/src
COPY target/Movie-API-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
