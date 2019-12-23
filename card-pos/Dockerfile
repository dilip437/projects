FROM openjdk:8-jdk-alpine
WORKDIR /home/isha/docker-work-dir
COPY ./target/card-pos-1.0.0-SNAPSHOT.war ./card-pos.war
ENTRYPOINT ["java","-jar", "card-pos.war"]
