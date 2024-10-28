FROM eclipse-temurin:17-jre

ARG JAR_FILE
ADD target/build-service-0.0.1-SNAPSHOT.jar /usr/share/app.jar

ENTRYPOINT exec java $CUSTOM_JAVA_OPTS -Duser.timezone=Europe/Rome -jar /usr/share/app.jar
EXPOSE 8084