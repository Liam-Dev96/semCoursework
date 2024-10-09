FROM openjdk:latest
COPY ./target/semCoursework-1.0.1.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semCoursework-1.0.1.2-jar-with-dependencies.jar"]