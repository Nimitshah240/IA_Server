# Step 1: Build the project using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory inside the container
WORKDIR /ia_server

# Copy the contents of the ia_server project
COPY . .

# Package the application without running tests
RUN mvn clean package -DskipTests

# Step 2: Run the app using a slim JDK image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory for the runtime container
WORKDIR /ia_server

# Copy the built jar file from the build stage
COPY --from=build /ia_server/target/ia_server-v2.0.0.jar ia_server.jar

# Expose the port (default 8080)
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "ia_server.jar"]