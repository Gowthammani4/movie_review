# Set the base image
FROM maven:4.0.0-openjdk-21-slim

# Set the working directory
WORKDIR /app

# Copy the pom.xml and src files into the working directory
COPY pom.xml /app/pom.xml
COPY src /app/src

# Build the project
RUN mvn package -DskipTests

# Copy the JAR file to the working directory
COPY target/my-app-1.jar /app/app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]
