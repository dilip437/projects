FROM openjdk:8-jdk-alpine
WORKDIR /home/isha/docker-work-dir
COPY ./target/mastercard-inc-1.0.0-SNAPSHOT.war ./mastercard-inc.war
ENTRYPOINT ["java","-jar", "mastercard-inc.war"]
