# Travel Guide Project With Spring Boot And React

This project is a web application related to travel, allowing users to share travel information and manage their travel experiences. It has been developed using Spring Boot and deployed on Heroku.

## Project Demo

Check out the live demo of the project: [Travel Guide Web Application](https://jsuleyman.github.io/Travel-Guide-Web-Application-Fronted/#/)

## Features

- Provides a platform for users to share their travel experiences.
- Data storage and management are facilitated using databases like Redis and PostgreSQL.
- API documentation is automatically generated through integration with OpenAPI v3.
- User authentication and authorization mechanisms are established using Spring Security.
- Relationships are established between Data Transfer Objects (DTOs) and database entities.
- Gmail integration allows sending email notifications and communication.
- Supports user registration and login functionality.
- Works dynamically to tailor content according to user preferences.
- Allows users to upload travel photos with image upload capabilities.
- Custom exception classes and logging mechanisms are present for error handling.
- Logging is implemented to record and track events.
- Code quality and functionality are ensured through unit testing.

## Technology Stack

| Technology              | Description               |
|-------------------------|---------------------------|
| Core Framework          | Spring Boot               |
| Security Framework      | Spring Security, JWT      |
| Persistent Layer        | Spring Data JPA           |
| Database                | PostgreSQL                |
| Caching                 | Redis                     |

## Project Structure

- **authentication:** Manages application user authentication.
- **configuration:** Contains application configurations.
- **controllers:** Listens to client requests.
- **dao:** Data access objects for database interaction.
- **dto:** Data transfer objects for carrying data between processes.
- **enum:** Enumeration classes for maintaining constants.
- **exception:** Handles custom exception scenarios.
- **models:** Entity definitions representing database structures.
- **repository:** Communicates with the database.
- **service:** Contains business logic.
- **util:** Holds utility classes.
- **test/:** Contains unit and integration tests.
- **pom.xml:** Lists all project dependencies.

## Usage

To run the project locally, follow these steps:

1. Clone the repository.
2. Navigate to the project directory.
3. Install project dependencies using the specified tools (e.g., Maven).
4. Configure the database and other settings in `application.properties`.
5. Run the application using Spring Boot.
