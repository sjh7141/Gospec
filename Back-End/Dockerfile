FROM java:8

VOLUME /tmp

EXPOSE 8282

ARG JAR_FILE=target/GoSpec-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} GoSpec-api.jar

ENTRYPOINT ["java", "-jar", "/GoSpec-api.jar"]