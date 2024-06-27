Prerequisites
Docker
Git 

Getting Started

1.Clone the Repository
git clone <repository-url>
cd PCCW

2.Build and Deploy Application

Execute the deployment script run.sh using Git Bash 
bash run.sh

This script will:

Build the Angular frontend.
Build the Spring Boot backend.
Start containers using Docker Compose.

3.Access the Application:

Once the script completes successfully, open your web browser and navigate to:

http://localhost:4200

You should see the User Management application home page.


Notes:
If the application encounters issues or stops unexpectedly, use the following commands:
docker-compose down
docker-compose up -d
This will stop and remove the running containers and then restart them.
Ensure ports 4200 and 8080 are not used by other services on your machine to avoid conflicts.
