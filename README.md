# Lab 5: Spring Boot CRUD REST API

* Builds a REST API for managing students with Spring Boot, without a database
* Stores the data in an in-memory `ArrayList`, so it resets when the app restarts
* Supports Create, Read, Update and Delete through `@RestController` and `@RequestMapping("/students")`
* Endpoints: `GET /students`, `GET /students/{id}`, `POST /students`, `PUT /students/{id}`, `DELETE /students/{id}`
* Requires JDK 21, Maven and Spring Boot; tested with Postman at `http://localhost:8080/students`
