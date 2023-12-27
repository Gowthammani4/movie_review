# Set the base image
FROM maven:3.9.6-eclipse-temurin-21-jammy

# Set the working directory
WORKDIR /app

# Copy the pom.xml and src files into the working directory
COPY pom.xml /app/pom.xml
COPY src /app/src

# Build the project
RUN mvn package -DskipTests

# Copy the JAR file to the working directory
COPY /app/target/Movie-API-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]
