#!/bin/bash

# Build the Spring Boot application
./mvnw clean package

# Ensure the build was successful
if [ $? -ne 0 ]; then
  echo "Maven build failed. Exiting."
  exit 1
fi

# Build the Docker image
docker build -t user-management .

# Ensure the Docker build was successful
if [ $? -ne 0 ]; then
  echo "Docker build failed. Exiting."
  exit 1
fi

# Run the Docker container
docker run -p 8080:8080 user-management
