# Stage 1: Build the application using Maven and JDK 17
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy your project files to the container
COPY . .

# Package the application without running tests
RUN mvn clean package -DskipTests

# Stage 2: Run the app using JRE 17 (Alpine for smaller image)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the packaged jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app uses (default 8080)
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
