# Lab 6: Aspect-Oriented Programming with Spring Boot

* Adds cross-cutting concerns to the Lab 5 student CRUD API using Spring AOP and AspectJ annotations
* Logging aspect with `@Before`, `@AfterReturning`, `@AfterThrowing` and `@After`; performance and cache aspects with `@Around`
* Validation and security aspects use `@Before` with custom annotations such as `@RequireAdmin`, and `GlobalExceptionHandler` returns 400, 403 and 404
* Demonstrates pointcut designators (`execution`, `within`, `bean`, `target`, `@annotation`), `@Order`, and Spring's CGLIB proxies
* Requires JDK 21, Maven and Spring Boot; tested with Postman at `http://localhost:8080/students`
