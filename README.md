# Event Ticketing System

A comprehensive event ticketing system built with Spring Boot that allows event owners to create and manage events, generate tickets, and track attendance with real-time updates.

## Features

- **User Authentication & Authorization**: Secure registration and login system
- **Event Management**: Create, update, delete, and view events (owners only)
- **Ticket Management**: Generate tickets for events and track sales
- **Public Ticket Purchase**: Anyone can purchase tickets without authentication
- **Attendance Tracking**: Scan tickets to mark attendance
- **Real-time Updates**: Server-Sent Events (SSE) for live notifications
- **Caching**: Caffeine caching for improved performance
- **Dashboard**: Comprehensive insights and reporting
- **Containerized**: Docker support for easy deployment

## Technology Stack

- **Backend**: Spring Boot 3.x, Spring Security, Spring Data JPA
- **Database**: PostgreSQL
- **Caching**: Caffeine
- **Real-time**: Server-Sent Events (SSE)
- **Authentication**: JWT
- **Containerization**: Docker & Docker Compose
- **Documentation**: Swagger/OpenAPI

## API Endpoints

### Authentication
- `POST /api/v1/auth/signup` - User registration
- `POST /api/v1/auth/login` - User login

### Events (Public Access)
- `GET /api/v1/events` - View all events
- `GET /api/v1/events/{id}` - View specific event
- `GET /api/v1/events/{id}/tickets` - View tickets for an event

### Events (Authenticated - Event Owners Only)
- `POST /api/v1/events` - Create new event
- `PUT /api/v1/events/{id}` - Update event (owner only)
- `DELETE /api/v1/events/{id}` - Delete event (owner only)
- `GET /api/v1/events/my-events` - Get user's events

### Ticket Management (Authenticated - Event Owners Only)
- `POST /api/v1/events/{id}/tickets` - Create tickets for event
- `POST /api/v1/tickets/{id}/scan` - Mark ticket as scanned

### Ticket Purchase (Public Access)
- `POST /api/v1/events/{eventId}/tickets/{ticketId}/purchase` - Purchase ticket

### Dashboard & Reports (Authenticated)
- `GET /api/v1/events/dashboard/insights` - Global dashboard insights
- `GET /api/v1/events/dashboard/my-insights` - User-specific insights

### Real-time Updates
- `GET /api/v1/sse/subscribe?clientId={id}` - Subscribe to real-time updates

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- Docker & Docker Compose (for containerized deployment)

### Running with Docker (Recommended)

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd event-ticketing-system
   ```

2. **Build and run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

3. **Access the application**
   - API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html

### Running Locally

1. **Clone and setup database**
   ```bash
   git clone <repository-url>
   cd event-ticketing-system
   
   # Setup PostgreSQL database
   createdb ticketing
   ```

2. **Configure application properties**
   ```yaml
   # src/main/resources/application.yml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/ticketing
       username: your_username
       password: your_password
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

## Usage Examples

### 1. User Registration
```bash
curl -X POST http://localhost:8080/api/v1/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phoneNumber": "+1234567890",
    "password": "password123"
  }'
```

### 2. Create Event (Authenticated)
```bash
curl -X POST http://localhost:8080/api/v1/events \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "eventName": "Tech Conference 2024",
    "description": "Annual tech conference",
    "startTime": "2024-06-15T09:00:00",
    "endTime": "2024-06-15T17:00:00",
    "venue": "Convention Center"
  }'
```

### 3. Purchase Ticket (Public)
```bash
curl -X POST http://localhost:8080/api/v1/events/{eventId}/tickets/{ticketId}/purchase \
  -H "Content-Type: application/json" \
  -d '{
    "purchaserEmail": "customer@example.com",
    "quantity": 2
  }'
```

### 4. Real-time Updates
```javascript
const eventSource = new EventSource('http://localhost:8080/api/v1/sse/subscribe?clientId=client123');

eventSource.onmessage = function(event) {
  const data = JSON.parse(event.data);
  console.log('Real-time update:', data);
};
```

## Security Model

- **Public Access**: Event viewing, ticket purchasing, SSE subscriptions
- **Authenticated Access**: Event management, ticket creation, attendance scanning
- **Ownership Validation**: Users can only manage their own events and tickets
- **JWT Authentication**: Secure token-based authentication

## Docker Commands

```bash
# Build and run
docker-compose up --build

# Run in background
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop services
docker-compose down

# Remove volumes
docker-compose down -v
```

## Development

### Project Structure
```
src/
├── main/
│   ├── java/com/bash/Event/ticketing/
│   │   ├── authentication/     # Auth components
│   │   ├── config/            # Configuration classes
│   │   ├── event/             # Event & ticket management
│   │   └── email/             # Email services
│   └── resources/
│       ├── application.yml    # Main configuration
│       └── application-docker.yml # Docker configuration
├── Dockerfile
├── docker-compose.yml
└── README.md
```

### Adding New Features
1. Create appropriate DTOs in `dto/request` and `dto/response`
2. Add service interfaces and implementations
3. Create controller endpoints
4. Update security configuration if needed
5. Add tests

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## License

This project is licensed under the MIT License.