FROM openjdk:17-alpine

ARG VERSION

WORKDIR /usr/src/autobot

COPY ./out/artifacts/AutoBot_jar/AutoBot.jar /usr/src/autobot

CMD ["java", "-jar", "AutoBot.jar"]