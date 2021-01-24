FROM java:8-jdk-alpine

COPY ./target/recipe-api-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch recipe-api-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","recipe-api-0.0.1-SNAPSHOT.jar"]