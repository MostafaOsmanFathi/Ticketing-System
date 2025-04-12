# Ticketing System

## Overview

The **Ticketing System** is a Java-based GUI application for managing and tracking support tickets. It serves as an educational project to practice **Object-Oriented Programming (OOP)** and **system design** in Java. The application supports multiple database sources, including **SQLite** and **MySQL**.

## Unit Testing

For project unit tests, see [Testing Guide](/TicketingSystem/src/test/java/com/ticketing/README.md).

## Features

- Create, update, and delete tickets
- Assign tickets to users
- Track ticket statuses
- Persistent storage with MySQL or SQLite
- GUI-based user interface
- Follows a **layered architecture** for better separation of concerns

## Technologies

- **Java 17+**
- **JDBC** (Database connection)
- **MySQL / SQLite** (Persistent storage)
- **JUnit** (Testing framework)
- **Maven** (Dependency management)

## Project Structure

The project follows a **layered architecture**, organizing code into distinct layers for maintainability and scalability.

```
TicketingSystem
+---main
|   +---java
|   |   \---com
|   |       \---ticketing
|   |           |   Main.java
|   |           |   
|   |           +---Control
|   |           +---enums
|   |           |       AccountType.java
|   |           |       
|   |           +---model
|   |           |       Account.java
|   |           |       Customer.java
|   |           |       CustomerTicket.java
|   |           |       Event.java
|   |           |       EventOrganizer.java
|   |           |       TicketType.java
|   |           |
|   |           +---repository
|   |           |       AccountRepository.java
|   |           |       DatabaseRepository.java
|   |           |       MySqlRepository.java
|   |           |       SqlLiteDatabaseRepository.java
|   |           |       TicketingRepository.java
|   |           |
|   |           +---service
|   |           |       AccountService.java
|   |           |       CustomerService.java
|   |           |       EventOrganizerService.java
|   |           |       PaymentInterface.java
|   |           |       sudoPayment.java
|   |           |
|   |           \---view
|   |                   AccountServicesPage.java
|   |                   BaseWindow.java
|   |                   BrowseAllTicketTypesPage.java
|   |                   BrowseEventPage.java
|   |                   BrowseMyCustomerTicketsPage.java
|   |                   CreateEventPage.java
|   |                   CreateTicketTypePage.java
|   |                   CustomerServicesPage.java
|   |                   DatabaseSelectionPage.java
|   |                   EventOrganizerServicesPage.java
|   |                   LoginPage.java
|   |                   MainMenu.java
|   |                   MySQLConfigPage.java
|   |                   RegisterPage.java
|   |                   ShowEventPage.java
|   |                   SQLiteConfigPage.java
|   |
|   \---resources
|       |   config.properties
|       |
|       +---db
|       |       schema.sql
|       |       seed.sql
|       |       sqliteSchema.sql
|       |
|       \---META-INF
|               MANIFEST.MF
|
\---test
    \---java
        \---com
            \---ticketing
                |   README.md
                |
                +---model
                |       AccountTest.java
                |       CustomerTest.java
                |       CustomerTicketTest.java
                |       EventOrganizerTest.java
                |       EventTest.java
                |       TicketTypeTest.java
                |
                +---repository
                |       DatabaseRepositoryTest.java
                |
                \---service
                        AccountServiceTest.java
                        CustomerServiceTest.java
                        EventOrganizerServiceTest.java
```

## Setup

### Prerequisites

- **Java 17+**
- **Maven**
- **MySQL or SQLite** (depending on database selection)

## How to Use

See the [How to Use Guide](HowToUse.md) for a visual, step-by-step walkthrough of the application.

## Running the Application

To run the **Ticketing System**:

1. Download the latest `TicketingSystem.jar` from the [Releases](https://github.com/MostafaOsmanFathi/Ticketing-System/releases/tag/v0.1.1).
2. Use the following command to run the application or run using java runtime:

    ```bash
    java -jar TicketingSystem.jar
    ```

## License

Licensed under the **MIT License**.

## Contact

For inquiries, contact **Mostafa Osman** at [mostafa.osman.fathi@gmail.com](mailto:mostafa.osman.fathi@gmail.com).

