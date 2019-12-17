FROM openjdk:11

COPY ./target/movierental-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch movierental-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","movierental-0.0.1-SNAPSHOT.jar"]