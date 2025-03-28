# Unit Testing for the Application

## Overview
This directory contains unit tests for various components of the application, ensuring the correctness and reliability of the system. The tests are structured into different packages based on their respective modules.

## Project Structure
```
│   README.md
│   
├───model
│       AccountTest.java
│       CustomerTest.java
│       CustomerTicketTest.java
│       EventOrganizerTest.java
│       EventTest.java
│       TicketTypeTest.java
│       
├───repository
│       DatabaseRepositoryTest.java
│
└───service
        AccountServiceTest.java
        CustomerServiceTest.java
        EventOrganizerServiceTest.java
```

## Test Packages
### **1. Model Tests (`model/`):**
Tests for domain models and entities:
- `AccountTest.java` - Tests for `Account` entity.
- `CustomerTest.java` - Tests for `Customer` entity.
- `CustomerTicketTest.java` - Tests for `CustomerTicket` functionality.
- `EventOrganizerTest.java` - Tests for `EventOrganizer` entity.
- `EventTest.java` - Tests for `Event` entity.
- `TicketTypeTest.java` - Tests for `TicketType` functionality.

### **2. Repository Tests (`repository/`):**
Tests for data access layer:
- `DatabaseRepositoryTest.java` - Tests database interactions.

### **3. Service Tests (`service/`):**
Tests for business logic and service layer:
- `AccountServiceTest.java` - Tests for account-related services.
- `CustomerServiceTest.java` - Tests for customer management services.
- `EventOrganizerServiceTest.java` - Tests for event organizer services.

## Running the Tests
Ensure you have a testing framework like **JUnit** set up in your project. To run the tests:

### **Using Maven:**

```sh
    cd /path/to/project-root
    mvn test
```

## Testing Framework
- The tests use **JUnit 5 (Jupiter)**.
- Mocking is handled using **Mockito** (if applicable).

## Contribution
To contribute:
1. Fork the repository.
2. Write additional tests or improve existing ones.
3. Submit a pull request with a description of the changes.

## License
This project is licensed under the MIT License.

