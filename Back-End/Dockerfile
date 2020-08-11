FROM java:8

VOLUME /home/ubuntu/docker

EXPOSE 8282

ARG JAR_FILE=target/GoSpec-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} GoSpec-api.jar

ENTRYPOINT ["java", "-jar", "GoSpec-0.0.1-SNAPSHOT.jar"]