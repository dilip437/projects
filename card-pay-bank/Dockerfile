FROM openjdk:8-jdk-alpine
WORKDIR /home/isha/docker-work-dir
COPY ./target/card-pay-bank-1.0.0-SNAPSHOT.war ./card-pay-bank.war
ENTRYPOINT ["java","-jar", "card-pay-bank.war"]
