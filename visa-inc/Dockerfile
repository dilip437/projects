FROM openjdk:8-jdk-alpine
WORKDIR /home/isha/docker-work-dir
COPY ./target/visa-inc-1.0.0-SNAPSHOT.war ./visa-inc.war
ENTRYPOINT ["java","-jar", "visa-inc.war"]
