# Atomic Inventory System 🚀

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.2-green) ![License](https://img.shields.io/badge/License-MIT-blue)

A robust, high-performance Inventory Management System designed to demonstrate advanced concurrency handling in a Spring Boot application. Built with a focus on data integrity, TDD, and clean architecture.

## 🔥 Key Features

- **Concurrency Safe**: Implements **Pessimistic Locking** (`PESSIMISTIC_WRITE`) to prevent race conditions during simultaneous stock updates.
- **RESTful API**: Clean, accessible endpoints documented with **OpenAPI (Swagger)**.
- **Robust Error Handling**: Centralized `GlobalExceptionHandler` for consistent API responses.
- **TDD Approach**: Built with a "Test First" methodology, featuring integration tests that simulate high-concurrency scenarios (10+ threads).
- **Docker Ready**: Includes `Dockerfile` for containerized deployment.

## 🛠️ Tech Stack

- **Core**: Java 17, Spring Boot 3
- **Data**: Spring Data JPA, H2 Database (In-Memory)
- **Tools**: Maven, Lombok, Docker
- **Documentation**: SpringDoc OpenAPI (Swagger UI)

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven (optional, wrapper included)

### Run Locally

1. **Clone the repository**
   ```bash
   git clone https://github.com/GabrielOrtizRengifo/atomic-inventory.git
   cd atomic-inventory
   ```

2. **Run with Maven**
   ```bash
   mvn spring-boot:run
   ```

3. **Explore the API**
   Once started, access the interactive documentation:
   👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Run with Docker

```bash
docker build -t atomic-inventory .
docker run -p 8080:8080 atomic-inventory
```

## 🧪 Testing Concurrency

To verify the locking mechanism, run the integration test `ConcurrencyInventoryTest`. It simulates 10 concurrent threads attempting to purchase the same product simultaneously, ensuring stock never drops below zero.

```bash
mvn test -Dtest=ConcurrencyInventoryTest
```

## 👤 Author

**Gabriel Ortiz Rengifo**
*Backend Java Developer*
