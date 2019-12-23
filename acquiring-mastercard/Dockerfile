FROM openjdk:8-jdk-alpine
WORKDIR /home/isha/docker-work-dir
COPY ./target/acquiring-mastercard-1.0.0-SNAPSHOT.war ./acquiring-mastercard.war
ENTRYPOINT ["java","-jar", "acquiring-mastercard.war"]
