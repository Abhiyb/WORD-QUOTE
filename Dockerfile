# Use Java 17 (or 11 if needed)
FROM eclipse-temurin:17-jdk AS build

# Set working directory
WORKDIR /app

# Copy all files to container
COPY . /app

# Make mvnw executable
RUN chmod +x ./mvnw

# Build the application
RUN ./mvnw clean install -DskipTests

# Run the application
CMD ["java", "-jar", "target/*.jar"]
