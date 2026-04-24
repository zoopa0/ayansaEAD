# Product Service Microservice

A professional RESTful Product Catalogue API built with Spring Boot 3.5.x. This project follows clean architecture principles using the DTO pattern, includes robust validation, and integrates automated CI/CD.

---

##  Getting Started

To run the service locally, make sure you have **JDK 21** installed.

```bash
mvn spring-boot:run
```

---

##  Endpoints & CRUD Operations

This service provides full CRUD functionality with proper HTTP status codes and DTO mapping:

| Method | Endpoint              | Description                | Success Response |
| ------ | --------------------- | -------------------------- | ---------------- |
| GET    | /api/v1/products      | Retrieve all products      | 200 OK           |
| GET    | /api/v1/products/{id} | Retrieve a product by ID   | 200 OK           |
| POST   | /api/v1/products      | Create a new product       | 201 Created      |
| PUT    | /api/v1/products/{id} | Update an existing product | 200 OK           |
| DELETE | /api/v1/products/{id} | Delete a product           | 204 No Content   |

---

## Validation & Error Handling

The API ensures proper validation and structured error responses:

* **400 Bad Request**
  Triggered when invalid data is provided (e.g., blank name, invalid price).
  Returns a `ProblemDetail` response with clear error messages.

* **404 Not Found**
  Returned when a product with the given ID does not exist for GET, PUT, or DELETE operations.

---

##  Testing & Quality Assurance

* **MockMvc Tests**
  8 test cases covering all CRUD operations and validation logic (100% passing).

* **DTO Pattern**
  Uses:

    * `ProductRequest`
    * `ProductResponse`
      This ensures the internal `Product` entity is never exposed directly.

* **CI/CD Pipeline**
  Integrated with GitHub Actions to automatically run:

```bash
mvn clean install
```

on every push.

---

## API Documentation & Tools

* **Swagger UI**
  http://localhost:8080/swagger-ui/index.html

* **Postman Collection**
  Available at:
  `postman/product-service-lab2.json`

* **H2 Database Console**
  http://localhost:8080/h2-console

  JDBC URL:

```text
jdbc:h2:mem:productdb
```

---

##  Author

**Ayansa Adugna**
