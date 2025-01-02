FROM amazoncorretto:17-alpine3.17
WORKDIR /app
COPY build/libs/Store-0.0.1-SNAPSHOT.jar store.jar
ENTRYPOINT ["java","-jar","/app/store.jar"]