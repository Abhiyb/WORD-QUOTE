# ---- Build Stage ----
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy only the necessary files first (for dependency caching)
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy the actual source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# ---- Runtime Stage ----
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy only the built JAR file from the builder stage
COPY --from=builder /app/target/Quote-Word-0.0.1-SNAPSHOT.jar /app/Quote-Word.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/Quote-Word.jar"]
