FROM openjdk:17

COPY bot/target/bot-1.0.jar app.jar

ENTRYPOINT exec java -jar /app.jar