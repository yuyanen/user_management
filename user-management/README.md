# User Management Application

## Requirements
- Java 11
- Docker
- Maven

## Building and Running the Application
1. Clone the repository.
2. Build the application:
    ```sh
    ./build.sh
    ```
3. Access the application at `http://localhost:8080`.
4. Access Swagger documentation at `http://localhost:8080/swagger-ui.html`.

## API Endpoints
- `POST /api/users` - Register a new user.
- `GET /api/users/{id}` - Get user by ID.
- `GET /api/users` - Get all users.
- `PUT /api/users/{id}` - Update user by ID.
- `DELETE /api/users/{id}` - Soft delete user by ID.
