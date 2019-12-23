FROM openjdk:8-jdk-alpine
WORKDIR /home/isha/docker-work-dir
COPY ./target/acquiring-processor-1.0.0-SNAPSHOT.war ./acquiring-processor.war
ENTRYPOINT ["java","-jar", "acquiring-processor.war"]
