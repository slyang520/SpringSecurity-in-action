FROM java:8
EXPOSE 3000

VOLUME /tmp

ADD ./build/libs/slyangsecurity-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]




