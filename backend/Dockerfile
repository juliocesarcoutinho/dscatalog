FROM eclipse-temurin:21
LABEL authors="julio"
VOLUME /tmp
EXPOSE 8080
ADD ./target/backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]