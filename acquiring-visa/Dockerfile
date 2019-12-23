FROM openjdk:8-jdk-alpine
WORKDIR /home/isha/docker-work-dir
COPY ./target/acquiring-visa-1.0.0-SNAPSHOT.war ./acquiring-visa.war
ENTRYPOINT ["java","-jar", "acquiring-visa.war"]
