FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine

# Add app code to /code inside container image
ADD . /code

# Set working directory for subsequent commands
WORKDIR /code

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]