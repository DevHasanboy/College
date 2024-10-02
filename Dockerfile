

# Use a smaller base image for the runtime
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port that the Spring Boot application listens on
EXPOSE 8132
# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
