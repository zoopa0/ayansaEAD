# Lab 2 Reflection - Product Service

### 1. Why should the ProductRequest DTO carry the @Valid annotations instead of the Product entity itself?
The DTO (Data Transfer Object) represents the input from the outside world. By validating at the DTO level, we catch bad data at the **edge of the system** before it ever reaches our business logic or persistence layer. This keeps our domain entities clean and allows different validation rules for different operations (e.g., creating vs. updating).

### 2. What is the purpose of the Location header returned on a POST 201 Created response, and which HTTP specification mandates it?
The `Location` header provides the **URL of the newly created resource**, allowing the client to know exactly where to find it. This is mandated by the **RFC 7231** (specifically the HTTP/1.1 semantics) which states that for 201 responses, the server should point to the primary identifier of the new resource.

### 3. Explain the difference between @ControllerAdvice and @ExceptionHandler.
- **@ExceptionHandler:** This is used inside a **specific controller** to handle exceptions thrown by its own methods.
- **@ControllerAdvice:** This is a **global interceptor**. It allows you to handle exceptions across the **entire application** in one central place.
  **Usage:** Use `@ExceptionHandler` for logic unique to one controller; use `@ControllerAdvice` to maintain a consistent error format (like ProblemDetail) for the whole API.

### 4. What would happen to the database state between tests if you removed @Transactional on the test class?
Without `@Transactional`, the data created by one test (like a POST request) would **persist in the database** and remain there when the next test runs. This "leaky state" could cause other tests to fail because the database is no longer in a clean, predictable state.

### 5. What does RFC 9457 define, and why is it better than a generic error message?
**RFC 9457** defines the "Problem Details for HTTP APIs" (JSON error format). It is better because it provides a **standardized structure** (Type, Title, Status, Detail, Instance) that machines can parse and humans can easily read. This is much more helpful for debugging than a generic "something went wrong" message.

### 6. Difference between Integration Test (MockMvc) and Unit Test (Mockito).
- **Unit Test (Mockito):** Tests a single class in isolation (e.g., checking if the Service logic works by mocking the Repository). It is **fast** and used for complex business logic.
- **Integration Test (MockMvc):** Tests the **entire flow** from the HTTP request down to the database. It ensures that the Controller, Service, and Repository all work together correctly.
  **Preference:** Use Unit tests for logic-heavy methods; use Integration tests to verify API endpoints and security.