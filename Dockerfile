# Use the correct Java version (21)
FROM eclipse-temurin:21-jdk AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY . /app

# Ensure Maven wrapper is executable
RUN chmod +x mvnw

# Use .m2 cache to speed up builds
RUN --mount=type=cache,target=/root/.m2 ./mvnw clean install -DskipTests || (cat target/mvn-dependency-list.log && exit 1)

# Use runtime image for smaller final build
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Run the application
CMD ["java", "-jar", "app.jar"]
