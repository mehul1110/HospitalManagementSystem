# Hospital Management System

## Overview

A **Spring Boot** based RESTful API that manages patients, users, and authentication for a hospital. The project demonstrates:

- **JWT based authentication** (`JwtService`, `JwtAuthenticationFilter`).
- **Spring Security** configuration (`SecurityConfig`).
- **CRUD operations** for `Patient` and `User` entities via Spring Data JPA repositories.
- **Layered architecture** with separate packages for **configuration, controller, service, DTO, entity, and repository**.
- Maven build with a **Maven Wrapper** (`mvnw`/`mvnw.cmd`).

## Project Structure

```
src/main/java/com/example/hms/
│
├─ config/                # Spring configuration & JWT utilities
│   ├─ ApplicationConfig.java   # General bean definitions
│   ├─ JwtService.java          # JWT creation/validation
│   ├─ JwtAuthenticationFilter.java  # Auth filter for requests
│   └─ SecurityConfig.java      # HTTP security settings
│
├─ controller/            # REST controllers
│   ├─ AuthController.java      # /api/auth endpoints (login)
│   └─ PatientController.java   # /api/patients CRUD endpoints
│
├─ dto/                   # Data Transfer Objects
│   ├─ AuthRequest.java
│   ├─ AuthResponse.java
│   └─ PatientDto.java
│
├─ entity/                # JPA entity classes
│   ├─ Patient.java
│   ├─ Role.java
│   └─ User.java
│
├─ repository/            # Spring Data JPA repositories
│   ├─ PatientRepository.java
│   └─ UserRepository.java
│
├─ service/               # Business logic layer
│   ├─ AuthService.java
│   └─ PatientService.java
│
└─ HospitalManagementApplication.java   # Main Spring Boot entry point
```

## Building & Running

The repository includes the **Maven Wrapper**, so you can build without installing Maven globally:

```powershell
# Windows
./mvnw.cmd clean package   # builds the JAR
./mvnw.cmd spring-boot:run # runs the application
```

```bash
# macOS / Linux (or Git Bash on Windows)
./mvnw clean package
./mvnw spring-boot:run
```

The application starts on port **8080** by default and connects to an in‑memory H2 database (see `src/main/resources/application.properties`).

## Authentication Flow

1. **Login** – POST `/api/auth/login` with `username` and `password`.
2. **JwtService** creates a signed JWT containing the username and role.
3. **JwtAuthenticationFilter** validates the token on each request and populates the Spring Security context.
4. Secured endpoints (`/api/patients/**`) require the `Authorization: Bearer <token>` header.

## Contributing

- All new development should happen on the **`dev`** branch.
- Keep `master` limited to documentation (README) only.
- Follow standard Spring Boot coding conventions and add unit tests for new features.
