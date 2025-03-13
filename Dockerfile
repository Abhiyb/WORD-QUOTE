# Use Java 21 (Same as your local build)
FROM eclipse-temurin:21-jdk AS build

# Set working directory inside the container
WORKDIR /app

# Copy all project files
COPY . /app

# Ensure Maven wrapper is executable
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean install -DskipTests

# Use a minimal runtime image
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Set the command to run the application
CMD ["java", "-jar", "app.jar"]
