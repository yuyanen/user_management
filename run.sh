#!/bin/bash


# Build Angular frontend
echo "Building Angular frontend..."
(cd user-management-frontend && docker build -t user-management-frontend .)

# Build Email service
echo "Building Spring Boot backend..."
(cd email-service && ./mvnw package -DskipTests)
(cd email-service && docker build -t email-service .)

# Build User management
echo "Building Spring Boot backend..."
(cd user-management && ./mvnw package -DskipTests)
(cd user-management && docker build -t user-management .)

# Build Docker images using docker-compose with --no-cache option
echo "Building Docker images with --no-cache option..."
docker-compose build --no-cache

# Start containers using Docker Compose
echo "Starting containers..."
docker-compose up -d

echo "Application deployed successfully."
