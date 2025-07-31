# Java Training - JWT Authentication Implementation

This Spring Boot application now includes JWT (JSON Web Token) authentication for secure API access.

## Features

- JWT-based authentication
- User registration and login
- Password encryption using BCrypt
- Protected and public endpoints
- Stateless authentication

## API Endpoints

### Authentication Endpoints (Public)

#### Register a new user
```
POST /api/auth/register
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "surname": "Doe",
    "mobile": "1234567890"
}
```

#### Login
```
POST /api/auth/login
Content-Type: application/json

{
    "email": "john@example.com",
    "password": "password123"
}
```

Response:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "email": "john@example.com",
    "name": "John Doe",
    "message": "Login successful"
}
```

### Protected Endpoints (Require JWT Token)

#### Get all users
```
GET /api/users/all
Authorization: Bearer <your-jwt-token>
```

#### Get user by ID
```
GET /api/users/{id}
Authorization: Bearer <your-jwt-token>
```

#### Create user
```
POST /api/users/create
Authorization: Bearer <your-jwt-token>
Content-Type: application/json

{
    "name": "Jane Doe",
    "email": "jane@example.com",
    "password": "password123"
}
```

#### Update user
```
PUT /api/users/{id}
Authorization: Bearer <your-jwt-token>
Content-Type: application/json

{
    "name": "Updated Name",
    "email": "updated@example.com"
}
```

#### Delete user
```
DELETE /api/users/{id}
Authorization: Bearer <your-jwt-token>
```

### Test Endpoints

#### Public endpoint (no authentication required)
```
GET /api/test/public
```

#### Protected endpoint (authentication required)
```
GET /api/test/protected
Authorization: Bearer <your-jwt-token>
```

## Configuration

JWT configuration is in `application.properties`:

```properties
jwt.secret=mySecretKey123456789012345678901234567890123456789012345678901234567890
jwt.expiration=86400000
```

- `jwt.secret`: Secret key for signing JWT tokens
- `jwt.expiration`: Token expiration time in milliseconds (24 hours by default)

## Security Features

1. **Password Encryption**: All passwords are encrypted using BCrypt before storing in the database
2. **JWT Token Validation**: Tokens are validated on each request to protected endpoints
3. **Stateless Authentication**: No server-side session storage required
4. **CORS Support**: Cross-origin requests are enabled for all origins

## Usage Example

1. **Register a new user**:
   ```bash
   curl -X POST http://localhost:8080/api/auth/register \
     -H "Content-Type: application/json" \
     -d '{"name":"John","email":"john@example.com","password":"password123"}'
   ```

2. **Login to get JWT token**:
   ```bash
   curl -X POST http://localhost:8080/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{"email":"john@example.com","password":"password123"}'
   ```

3. **Use the token to access protected endpoints**:
   ```bash
   curl -X GET http://localhost:8080/api/users/all \
     -H "Authorization: Bearer <your-jwt-token>"
   ```

## Project Structure

- `JwtUtil.java`: JWT token generation and validation
- `JwtAuthenticationFilter.java`: JWT authentication filter
- `SecurityConfig.java`: Spring Security configuration
- `AuthController.java`: Authentication endpoints
- `AuthService.java`: Authentication business logic
- `CustomUserDetailsService.java`: User details service for Spring Security

## Running the Application

1. Ensure MySQL is running and the database is configured
2. Run the application: `mvn spring-boot:run`
3. The application will start on `http://localhost:8080` 