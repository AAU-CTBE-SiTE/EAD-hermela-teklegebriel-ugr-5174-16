# Customer and Order Microservices

A microservices project built using Spring Boot 3.3 and Java 17.

## Customer Service
- Port: 8080
- Database: MySQL
- Database name: customerdb

## Order Service
- Port: 9090
- Database: MongoDB
- Database name: orderdb

## Technologies
- Java 17
- Spring Boot 3.3
- Maven
- Spring Data JPA
- Spring Data MongoDB
- MySQL
- MongoDB
- Lombok
- REST APIs

## Running the project
1. Start MySQL.
2. Start MongoDB.
3. Run CustomerServiceApplication.
4. Run OrderServiceApplication.

## Testing with Postman
- POST http://localhost:8080/customers
- POST http://localhost:9090/orders
- GET http://localhost:8080/customers/1/orders
