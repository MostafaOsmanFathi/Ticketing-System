CREATE DATABASE IF NOT EXISTS TicketingSystem;

USE TicketingSystem;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS CustomerTicket;
DROP TABLE IF EXISTS TicketType;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS EventOrganizer;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Account;

CREATE TABLE IF NOT EXISTS Account
(
    idAccount INT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(45),
    email     VARCHAR(45),
    password  VARCHAR(45),
    balance   DOUBLE
);

CREATE TABLE IF NOT EXISTS Customer
(
    idCustomer        INT PRIMARY KEY AUTO_INCREMENT,
    Account_idAccount INT,
    FOREIGN KEY (Account_idAccount) REFERENCES Account (idAccount)
);

CREATE TABLE IF NOT EXISTS EventOrganizer
(
    idEventOrganizer  INT PRIMARY KEY AUTO_INCREMENT,
    Account_idAccount INT,
    FOREIGN KEY (Account_idAccount) REFERENCES Account (idAccount)
);

CREATE TABLE IF NOT EXISTS Event
(
    idEvent                         INT PRIMARY KEY AUTO_INCREMENT,
    eventName                       VARCHAR(45),
    eventType                       VARCHAR(45),
    eventDescription                VARCHAR(255),
    EventOrganizer_idEventOrganizer INT,
    FOREIGN KEY (EventOrganizer_idEventOrganizer) REFERENCES EventOrganizer (idEventOrganizer)
);

CREATE TABLE IF NOT EXISTS TicketType
(
    idTicketType      INT PRIMARY KEY AUTO_INCREMENT,
    ticketPrice       DOUBLE,
    ticketName        VARCHAR(45),
    expirationDate    VARCHAR(45),
    numberOfTickets   INT,
    Event_idEvent     INT,
    EventOrganizer_id INT,
    FOREIGN KEY (Event_idEvent) REFERENCES Event (idEvent),
    FOREIGN KEY (EventOrganizer_id) REFERENCES EventOrganizer (idEventOrganizer)
);

CREATE TABLE IF NOT EXISTS CustomerTicket
(
    idCustomerTicket        INT PRIMARY KEY AUTO_INCREMENT,
    Customer_idCustomer     INT,
    TicketType_idTicketType INT,
    FOREIGN KEY (Customer_idCustomer) REFERENCES Customer (idCustomer),
    FOREIGN KEY (TicketType_idTicketType) REFERENCES TicketType (idTicketType)
);

SET FOREIGN_KEY_CHECKS = 1;
