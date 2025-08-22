# Reactive Spring Demo Application

This is a demo Spring Boot application showcasing reactive programming with WebFlux and gRPC integration.

## Features

- **Reactive REST API**: WebFlux-based REST endpoints for user data
- **Classic REST API**: Traditional blocking REST endpoints for comparison
- **gRPC Service**: High-performance gRPC endpoints with streaming support
- **External API Integration**: Fetches data from DummyJSON API
- **Reactive Data Processing**: Uses Project Reactor for non-blocking operations

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Building and Running

```bash
# Build the application
mvn clean compile

# Run tests
mvn test

# Start the application
mvn spring-boot:run
```

## API Endpoints

### REST Endpoints (HTTP)

The application starts on port 8080 with the following endpoints:

#### Reactive Endpoints (`/reactive`)
- `GET /reactive/users` - Stream all users (reactive)
- `GET /reactive/usernames` - Stream user names (reactive)

#### Classic Endpoints (`/classic`)
- `GET /classic/users` - Get all users (blocking)
- `GET /classic/usernames` - Get user names (blocking)

### gRPC Endpoints

The gRPC server starts on port 9090 with the following services:

#### UserService
- `GetUsers()` - Stream all users
- `GetUserCount()` - Get total user count  
- `GetUsersByPage(page, totalPages)` - Stream users for a specific page

### gRPC Service Definition

The service is defined in `src/main/proto/user_service.proto`:

```protobuf
service UserService {
  rpc GetUsers(GetUsersRequest) returns (stream UserResponse);
  rpc GetUserCount(GetUserCountRequest) returns (UserCountResponse);
  rpc GetUsersByPage(GetUsersByPageRequest) returns (stream UserResponse);
}
```

### Testing gRPC

You can test the gRPC endpoints using tools like:
- grpcurl (command-line tool)
- BloomRPC (GUI client)
- gRPC client in your preferred language

Example with grpcurl:
```bash
# Get user count
grpcurl -plaintext localhost:9090 com.example.demo.reactive.grpc.UserService/GetUserCount

# Stream all users
grpcurl -plaintext localhost:9090 com.example.demo.reactive.grpc.UserService/GetUsers
```

## Architecture

The application uses a layered architecture:

1. **Controllers/gRPC Services**: Handle HTTP/gRPC requests
2. **Service Layer**: Business logic using reactive streams
3. **External API Integration**: WebClient for reactive HTTP calls
4. **Data Mapping**: Convert between domain models and API responses

## Configuration

### Application Properties

```properties
# Basic configuration
spring.application.name=reactive

# gRPC Server Configuration  
grpc.server.port=9090
grpc.server.address=0.0.0.0
```

## Development

### Adding New gRPC Services

1. Update `src/main/proto/user_service.proto`
2. Run `mvn compile` to generate Java classes
3. Implement service in a `@GrpcService` annotated class
4. Add integration tests

### Code Generation

Protocol Buffer classes are automatically generated during Maven compile phase using the protobuf-maven-plugin.