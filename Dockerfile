FROM openjdk:17-jdk-slim-buster

WORKDIR /ruleEngine

COPY /target/RuleEngine-0.0.1-SNAPSHOT.jar /ruleEngine/RuleEngine.jar

ENTRYPOINT java -jar RuleEngine.jar