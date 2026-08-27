# Hospital Management System - Auth Service
● Student Name: Namal Dilmith Ruwanpathirana
<br><br>
● Student Number: 2301671058
<br><br>
● Slack Handle : namaldilmith2
<br><br>
● GCP Project ID : pro-edu-476313
<br><br>
A robust authentication microservice for the Hospital Management System built with Spring Boot, providing JWT-based authentication and authorization capabilities.

## Overview

The Auth Service is a dedicated microservice responsible for:
- User authentication and token generation
- JWT token validation and refresh
- User credential management
- Role-based access control (RBAC)
- Service-to-service communication via Eureka

## Technology Stack

- **Framework**: Spring Boot 4.1.0
- **Java Version**: 25
- **Security**: JWT (JJWT 0.11.5)
- **Service Discovery**: Spring Cloud Netflix Eureka
- **Configuration Management**: Spring Cloud Config
- **Build Tool**: Maven

## Project Structure

```
hospital-management-system-auth-service/
├── pom.xml                          # Maven configuration
├── README.md                         # This file
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       └── authservice/
│   │   │           ├── AuthServiceApplication.java
│   │   │           ├── controller/
│   │   │           ├── service/
│   │   │           ├── repository/
│   │   │           ├── model/
│   │   │           ├── security/
│   │   │           └── exception/
│   │   └── resources/
│   │       ├── application.yml
│   │       └── application-[profile].yml
│   └── test/
│       └── java/
```

## Key Features

### 1. **JWT Authentication**
- Token generation upon successful login
- Token validation and expiration handling
- Refresh token support for improved user experience

### 2. **Eureka Service Discovery**
- Automatic service registration and discovery
- Load balancing support for distributed systems

### 3. **Configuration Management**
- Centralized configuration via Spring Cloud Config
- Environment-specific configurations (dev, staging, prod)

### 4. **Actuator Endpoints**
- Health checks and monitoring
- Application metrics and statistics

## Prerequisites

- Java 25
- Maven 3.6 or higher
- Spring Cloud Config Server (for configuration management)
- Eureka Server (for service discovery)

## Installation

### 1. Clone the Repository
```bash
git clone https://github.com/namal1230/hospital-management-system-auth-service.git
cd hospital-management-system-auth-service
```

### 2. Build the Project
```bash
mvn clean package
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

Or using the compiled JAR:
```bash
java -jar target/AuthService-0.0.1-SNAPSHOT.jar
```

## Configuration

### Application Properties

Create an `application.yml` file in `src/main/resources/`:

```yaml
spring:
  application:
    name: auth-service
  cloud:
    config:
      uri: http://localhost:8888
    discovery:
      client:
        serviceUrl:
          defaultZone: http://localhost:8761/eureka/

server:
  port: 8081

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics

jwt:
  secret: your-secret-key-here
  expiration: 86400000  # 24 hours in milliseconds
  refreshExpiration: 604800000  # 7 days in milliseconds
```

### Environment Variables

Set the following environment variables as needed:
- `EUREKA_URI`: Eureka Server URI (default: http://localhost:8761/eureka/)
- `CONFIG_SERVER_URI`: Spring Cloud Config Server URI (default: http://localhost:8888)
- `JWT_SECRET`: Secret key for JWT token signing
- `DB_URL`: Database connection URL (if applicable)

## API Endpoints

### Authentication
- `POST /auth/login` - User login
- `POST /auth/refresh` - Refresh access token
- `POST /auth/logout` - User logout
- `POST /auth/validate` - Validate token

### User Management
- `POST /auth/users` - Create new user
- `GET /auth/users/{id}` - Get user details
- `PUT /auth/users/{id}` - Update user
- `DELETE /auth/users/{id}` - Delete user

### Health & Monitoring
- `GET /actuator/health` - Service health status
- `GET /actuator/info` - Application information
- `GET /actuator/metrics` - Application metrics

## Dependencies

### Core Dependencies
- **spring-boot-starter-webmvc**: Web MVC framework for REST APIs
- **spring-boot-starter-actuator**: Monitoring and management endpoints
- **jjwt-api, jjwt-impl, jjwt-jackson**: JWT token handling (v0.11.5)

### Cloud Dependencies
- **spring-cloud-starter-netflix-eureka-client**: Service discovery
- **spring-cloud-starter-config**: Configuration management

### Spring Cloud Version
- **2025.1.2**: Latest stable version compatible with Spring Boot 4.1.0

## Development

### Running Tests
```bash
mvn test
```

### Code Quality
```bash
# Run with code coverage
mvn clean test jacoco:report

# Check with SpotBugs
mvn spotbugs:check
```

### Building for Production
```bash
mvn clean package -P prod
```

## Docker Support

### Build Docker Image
```bash
docker build -t hospital-auth-service:latest .
```

### Run Docker Container
```bash
docker run -d \
  -p 8081:8081 \
  -e EUREKA_URI=http://eureka-server:8761/eureka/ \
  -e JWT_SECRET=your-secret \
  --name hospital-auth-service \
  hospital-auth-service:latest
```

## Deployment

### Kubernetes
Create a deployment manifest (`k8s/deployment.yaml`):
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: auth-service
spec:
  replicas: 3
  selector:
    matchLabels:
      app: auth-service
  template:
    metadata:
      labels:
        app: auth-service
    spec:
      containers:
      - name: auth-service
        image: hospital-auth-service:latest
        ports:
        - containerPort: 8081
```

### AWS, Azure, or GCP
Deploy using your cloud provider's container orchestration service (ECS, AKS, GKE).

## Security Considerations

- **JWT Secret**: Use a strong, randomly generated secret key in production
- **HTTPS**: Always use HTTPS in production environments
- **Token Expiration**: Set appropriate token expiration times
- **CORS**: Configure CORS policies to restrict cross-origin requests
- **Input Validation**: Validate all user inputs to prevent injection attacks
- **Rate Limiting**: Implement rate limiting on authentication endpoints

## Troubleshooting

### Service Won't Start
```bash
# Check Java version
java -version

# Check port availability
lsof -i :8081

# View application logs
tail -f logs/application.log
```

### Eureka Registration Issues
- Ensure Eureka Server is running on configured URI
- Check network connectivity to Eureka Server
- Verify `spring.cloud.discovery.client.serviceUrl.defaultZone`

### JWT Token Validation Errors
- Verify JWT secret is consistent across all services
- Check token expiration time
- Ensure token format is valid (Bearer token in Authorization header)

## Monitoring

### Health Check
```bash
curl http://localhost:8081/actuator/health
```

### Metrics
```bash
curl http://localhost:8081/actuator/metrics
```

### Logs
```bash
# Watch logs in real-time
tail -f logs/application.log

# Search for errors
grep ERROR logs/application.log
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is part of the Hospital Management System and is licensed under the MIT License.

## Support

For issues, questions, or suggestions:
- Create an issue on GitHub
- Contact the development team
- Check existing documentation and FAQs

## Changelog

### Version 0.0.1-SNAPSHOT
- Initial authentication service setup
- JWT token generation and validation
- Eureka service discovery integration
- Spring Cloud Config integration
- Basic user authentication endpoints

---

**Last Updated**: August 2026  
**Maintainer**: namal1230
