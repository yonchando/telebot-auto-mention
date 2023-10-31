FROM openjdk:17-alpine

WORKDIR /opt/app

COPY ./target/AutoBot-1.0.jar ./AutoBot.jar

CMD ["java", "-jar", "AutoBot.jar"]