FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:21
COPY --from=build/target/moviereview_app.jar moviereview_app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","moviereview_app.jar"]
