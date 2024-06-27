#!/bin/bash

# Build Angular frontend
echo "Building Angular frontend..."
(cd user-management-frontend && docker build -t user-management-frontend .)

# Build Spring Boot backend
echo "Building Spring Boot backend..."
(cd user-management-api && ./mvnw package -DskipTests)
(cd user-management-api && docker build -t user-management-api .)

# Start containers using Docker Compose
echo "Starting containers..."
docker-compose up -d

echo "Application deployed successfully."
