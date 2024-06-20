FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/user-management-0.0.1-SNAPSHOT.jar user-management.jar
ENTRYPOINT ["java", "-jar", "/user-management.jar"]
