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

## Running the Application Prerequisites

- Docker
- Docker Compose

## Getting Started

### 1. Clone the Repository

Clone this repository to your local machine:

```sh
git clone https://github.com/yourusername/drive-and-deliver.git
cd drive-and-deliver 


./mvnw clean package


docker-compose build
docker-compose up 


