#!/bin/bash

# 设置工作目录，确保路径正确
BASE_DIR=$(dirname "$0")
cd "$BASE_DIR"

# 构建 Angular 前端
echo "构建 Angular 前端..."
(cd user-management-frontend && docker build -t user-management-frontend .)

# 构建 Spring Boot 后端
echo "构建 Spring Boot 后端..."
(cd user-management && ./mvnw package -DskipTests && docker build -t user-management .)

# 启动容器使用 Docker Compose
echo "启动容器..."
docker-compose up -d

# 等待 email-service 启动
echo "等待 email-service 启动..."
until docker-compose exec email-service curl -sSf http://localhost:8081/actuator/health > /dev/null; do
    echo "等待 email-service..."
    sleep 5
done
echo "email-service 已启动，可以开始 user-management 的调用。"

# 检查容器状态
echo "检查容器状态..."
docker-compose ps

echo "应用部署成功。"
