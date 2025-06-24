# Use official OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/Groupe12Alinfo42425-1.4.0-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8086

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
