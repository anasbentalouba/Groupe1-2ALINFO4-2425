# Use a lightweight OpenJDK image
FROM eclipse-temurin:17-jdk-alpine

# Set app directory
WORKDIR /app

# Copy the jar file (you can copy the actual jar if you know the name)
COPY target/Groupe12Alinfo42425-1.4.0-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
