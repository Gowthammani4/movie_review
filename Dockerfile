FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/moviereview_app.jar moviereview_app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","moviereview_app.jar"]
