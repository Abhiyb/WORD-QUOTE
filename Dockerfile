# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project to the container
COPY . /app/

# Build the project inside the container (if you haven't built it locally)
RUN ./mvnw clean package -DskipTests

# Copy the JAR file to the final image
COPY target/Quote-Word-0.0.1-SNAPSHOT.jar /app/Quote-Word.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/Quote-Word.jar"]
