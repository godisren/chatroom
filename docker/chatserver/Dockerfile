FROM openjdk:11

ENV MQ_HOST 127.0.0.1

VOLUME /tmp
ADD chatroom-server-queue.jar app.jar

EXPOSE 8080

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar", "--mq.host=", ${MQ_HOST}]
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar --mq.host=$MQ_HOST
