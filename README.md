# DriveAndDeliver

## Overview
This project is a simple implementation of a delivery service system where customers can choose their delivery method and book time slots. The API follows HATEOAS principles and is documented using Swagger.

## Architecture
- **Controller**: Handles HTTP requests and responses.
- **Exception**: Handles custom exceptions.
- **Model**: Represents data structures.
- **Repositories**: Handles data persistence.
- **Service**: Contains business logic.
- **Test**: Contains unit and integration tests.

## Requirements
- Java 21
- Maven
- Docker (for containerization)
- Kubernetes (for deployment)

Use http://localhost:8080/swagger-ui/index.html to test the APIs

## Running the Application 
### Prerequisites 
- Java 21
- Maven 3.9.x
- Docker
- Docker Compose
- Kubernetes

### Steps to run the app: 

1. git clone https://github.com/yourusername/drive-and-deliver.git
2. cd drive-and-deliver
3. run "docker-compose up -d  postgres" 
4. run "./mvnw clean package"
5. run "docker-compose up -d  app"  
6. run "kubectl apply -f postgres-pv-pvc.yaml"
7. run "kubectl apply -f postgres-deployment.yaml"
8. run "kubectl apply -f app-deployment.yaml"
9. launch http://localhost:8080/swagger-ui/index.html 