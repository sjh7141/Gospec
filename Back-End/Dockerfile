FROM java:8

VOLUME /home/ubuntu/docker:/tmp

EXPOSE 8282

ARG JAR_FILE=target/GoSpec-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} GoSpec-api.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "/GoSpec-api.jar"]