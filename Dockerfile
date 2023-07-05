FROM openjdk:17

ARG VERSION

COPY . /usr/src/autobot

WORKDIR /usr/src/autobot

RUN .mvn/bin/mvn clean compile

#CMD ["java", "-jar", "target/AutoBot-1.0.jar"]
CMD [".mvn/bin/mvn", "exec:java","-D","exec.mainClass=com.yonchando.autobot.AutoBotApplicant"]

EXPOSE 8080