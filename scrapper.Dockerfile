FROM openjdk:17

COPY scrapper/target/scrapper-1.0.jar app.jar

ENTRYPOINT exec java -jar /app.jar

