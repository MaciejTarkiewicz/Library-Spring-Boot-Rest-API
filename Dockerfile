FROM maven:3.6.0-jdk-11-slim AS build
COPY src ./Library-Spring-Boot-Rest-API/src
COPY pom.xml ./Library-Spring-Boot-Rest-API
RUN mvn -f ./Library-Spring-Boot-Rest-API/pom.xml clean package -DskipTests

FROM openjdk:8u201-jdk-alpine3.9
COPY --from=build ./Library-Spring-Boot-Rest-API/target/library-app-0.0.1-SNAPSHOT.jar ./demo.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","./demo.jar"]
