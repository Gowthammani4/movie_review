FROM maven:4.0.0-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:21
COPY --from=build /target/moviereview_app.jar moviereview_app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","moviereview_app.jar"]