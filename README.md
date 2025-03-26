# Ticketing System

## Overview
The **Ticketing System** is a Java-based application designed to manage and track support tickets efficiently. This project serves as an educational exercise in implementing Object-Oriented Programming (OOP) principles and system design in Java.

## Features
- Create, update, and delete tickets
- Assign tickets to different users
- Track ticket statuses
- Database integration for persistent storage
- Well-structured package organization following MVC principles

## Technologies Used
- **Java** (Core programming language)
- **JDBC** (For making creating connection with mysql)
- **MySql** (Database options)
- **JUnit** (Testing framework)

## Project Structure
```
TicketingSystem
│── .idea/                    # IntelliJ IDEA project settings
│── src/
│   ├── main/
│   │   ├── java/com/ticketing/
│   │   │   ├── model/         # Entity classes
│   │   │   ├── repository/    # Database repositories
│   │   │   ├── service/       # Business logic
│   │   │   ├── util/          # Utility classes
│   │   ├── resources/
│   │       ├── db/            # Database configurations
│   ├── test/
│       ├── java/com/ticketing/
│       │   ├── model/         # Model tests
│       │   ├── repository/    # Repository tests
│       │   ├── service/       # Service tests
│── target/                    # Compiled classes and build artifacts
│── pom.xml                     # Maven build configuration
```

## Getting Started
### Prerequisites
- Java 17+
- Maven
- MySql (if using a persistent database)

## License
This project is licensed under the MIT License.

## Contact
For any inquiries, you can reach out to **Mostafa Osman** at mostafa.osman.fathi@gmail.com.

