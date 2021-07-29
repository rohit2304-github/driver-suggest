FROM openjdk:8
COPY target/driver-suggest.jar ./service.jar
EXPOSE 9080
ENTRYPOINT exec java $JAVA_OPTS -jar /service.jar