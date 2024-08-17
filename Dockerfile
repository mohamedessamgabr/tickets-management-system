FROM amazoncorretto:17-alpine3.17-jdk
WORKDIR /app
COPY target/tickets*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]