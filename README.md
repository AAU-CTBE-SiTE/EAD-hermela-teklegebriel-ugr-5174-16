# Lab 8: Spring Boot Student CRUD API

This project is a Student Management REST API developed using Spring Boot and MySQL. It allows users to manage student records through CRUD operations.

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman

## Project Features

- **Create Student:** Add new student records to the database.
- **Retrieve Students:** Get all students or find a student by ID.
- **Update Student:** Modify existing student information.
- **Delete Student:** Remove student records from the database.
- **Database Integration:** Connect Spring Boot to MySQL using Spring Data JPA and Hibernate.

## Student Information

Each student record contains:

- ID
- First Name
- Last Name
- Email
- Department

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/students` | Create a student |
| GET | `/students` | Retrieve all students |
| GET | `/students/{id}` | Retrieve a student by ID |
| PUT | `/students/{id}` | Update a student |
| DELETE | `/students/{id}` | Delete a student |

## Database Configuration

The application uses a MySQL database named `student_db`.

Hibernate is configured with `ddl-auto=update` to automatically create or update the database table based on the Student entity.

Set your database password using the `DB_PASSWORD` environment variable. Do not commit your actual database password.

## Testing

All CRUD operations were tested using Postman. The database operations were also verified directly in MySQL.

## AI Assistance

See [AI_Conversations.md](AI_Conversations.md) for the ChatGPT conversations used during the development of this project.
