FROM openjdk:8u201-jdk-alpine3.9
ADD target/library-app-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar ibrary-app-0.0.1-SNAPSHOT.jar