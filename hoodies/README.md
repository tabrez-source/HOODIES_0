pwd# Hoodies E-commerce Application

This is an e-commerce application for selling hoodies built with Spring Boot.

## Running Locally

### Prerequisites
- Java 17
- Maven
- Docker and Docker Compose

### Steps to Run

1. **Start PostgreSQL Database with Docker Compose**
   ```bash
   docker-compose up -d
   ```

2. **Build the Application**
   ```bash
   ./mvnw clean package
   ```

3. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. The application will be available at http://localhost:8080

## GitHub Actions CI/CD

This project includes GitHub Actions workflows for continuous integration and deployment:

### CI/CD Workflow (`ci-cd.yml`)
- Triggered on pushes to main/master branch and pull requests
- Builds the application with Maven
- Runs tests
- Builds a Docker image
- Uploads build artifacts

### Deployment Workflow (`deploy.yml`)
- Runs after successful completion of the CI/CD workflow
- Downloads the build artifacts
- Builds and pushes the Docker image to Docker Hub
- Prepared for deployment to your chosen platform

## Setting Up GitHub Secrets

For the deployment workflow to work, you need to set up the following secrets in your GitHub repository:

- `DOCKER_USERNAME`: Your Docker Hub username
- `DOCKER_PASSWORD`: Your Docker Hub access token or password

## Deployment Options

The deploy workflow is prepared for various deployment options:
- Kubernetes
- Cloud providers (AWS, Azure, GCP)
- Virtual Private Servers

Configure the deployment section in the `deploy.yml` file based on your preferred hosting solution. 