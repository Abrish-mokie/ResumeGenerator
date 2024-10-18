# Stage 2: Create the actual container with the built application
FROM openjdk:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the build stage to the runtime stage
COPY target/*.jar /app/resume.jar

# Expose the application port (if you're using the default port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "resume.jar"]