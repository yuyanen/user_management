#!/bin/bash

# Build Angular frontend
echo "Building Angular frontend..."
(cd user-management-frontend && docker build -t user-management-frontend .)

# Build Spring Boot backend for user management
echo "Building Spring Boot backend for user management..."
(cd user-management && ./mvnw package -DskipTests)
(cd user-management && docker build -t user-management .)

# Build Spring Boot backend for email service
echo "Building Spring Boot backend for email service..."
(cd mail-service && ./mvnw package -DskipTests)
(cd mail-service && docker build -t mail-service .)

# Start containers using Docker Compose
echo "Starting containers..."
docker-compose up -d

echo "Application deployed successfully."

