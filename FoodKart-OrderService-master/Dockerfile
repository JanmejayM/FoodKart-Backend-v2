FROM openjdk:8
WORKDIR /app
COPY /target/*.jar /app/target/
EXPOSE 8083
CMD ["java","-jar","/app/target/Order-Service-0.0.1-SNAPSHOT.jar"]