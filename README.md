# Ticketing System

## Overview

The **Ticketing System** is a Java-based application for managing and tracking support tickets. It serves as an educational project to practice **Object-Oriented Programming (OOP)** and **system design** in Java.

## Unit Testing

For project unit tests, see [Testing Guide](/TicketingSystem/src/test/java/com/ticketing/README.md).

## Features

- Create, update, and delete tickets
- Assign tickets to users
- Track ticket statuses
- Persistent storage with MySQL
- Follows a **layered architecture** for better separation of concerns

## Technologies

- **Java 17+**
- **JDBC** (MySQL database connection)
- **MySQL** (Persistent storage)
- **JUnit** (Testing framework)
- **Maven** (Dependency management)

## Project Structure

The project follows a **layered architecture**, organizing code into distinct layers for maintainability and scalability.

```
TicketingSystem
│── src/
│   ├── main/java/com/ticketing/
│   │   ├── model/         # Data layer (entities)
│   │   ├── repository/    # Data access layer (database interactions)
│   │   ├── service/       # Business logic layer (processing rules)
│   │   ├── util/          # Utility layer (helper functions)
│   ├── main/resources/db/ # Database configurations
│   ├── test/java/com/ticketing/
│       ├── model/         # Model tests
│       ├── repository/    # Repository tests
│       ├── service/       # Service tests
│── pom.xml                # Maven build file
```

## Setup

### Prerequisites

- **Java 17+**
- **Maven**
- **MySQL** (if using a database)

## License

Licensed under the **MIT License**.

## Contact

For inquiries, contact **Mostafa Osman** at [mostafa.osman.fathi@gmail.com](mailto:mostafa.osman.fathi@gmail.com).  

