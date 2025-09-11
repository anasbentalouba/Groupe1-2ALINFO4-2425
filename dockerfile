FROM openjdk:17
COPY target/Groupe12Alinfo42425-1.4.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

