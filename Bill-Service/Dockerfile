FROM openjdk:8
WORKDIR /app
COPY /target/*.jar /app/target/
EXPOSE 8761
CMD ["java","-jar","/app/target/Bill-Service-0.0.1-SNAPSHOT.jar"]