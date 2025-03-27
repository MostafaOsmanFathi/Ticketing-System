CREATE DATABASE IF NOT EXISTS TicketingSystem;

USE TicketingSystem;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS CustomerTicket;
DROP TABLE IF EXISTS TicketType;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS EventOrganizer;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Account;

CREATE TABLE Account
(
    idAccount INT PRIMARY KEY,
    name      VARCHAR(45),
    email     VARCHAR(45),
    password  VARCHAR(45),
    balance   DOUBLE
);

CREATE TABLE Customer
(
    idCustomer        INT PRIMARY KEY,
    Account_idAccount INT,
    FOREIGN KEY (Account_idAccount) REFERENCES Account (idAccount)
);

CREATE TABLE EventOrganizer
(
    idEventOrganizer  INT PRIMARY KEY,
    Account_idAccount INT,
    FOREIGN KEY (Account_idAccount) REFERENCES Account (idAccount)
);

CREATE TABLE Event
(
    idEvent                         INT PRIMARY KEY,
    eventName                       VARCHAR(45),
    eventType                       VARCHAR(45),
    eventDescription                VARCHAR(255),
    EventOrganizer_idEventOrganizer INT,
    FOREIGN KEY (EventOrganizer_idEventOrganizer) REFERENCES EventOrganizer (idEventOrganizer)
);

CREATE TABLE TicketType
(
    idTicketType      INT PRIMARY KEY,
    ticketPrice       DOUBLE,
    ticketName        VARCHAR(45),
    expirationDate    VARCHAR(45),
    numberOfTickets   INT,
    Event_idEvent     INT,
    EventOrganizer_id INT,
    FOREIGN KEY (Event_idEvent) REFERENCES Event (idEvent),
    foreign key (EventOrganizer_id) REFERENCES EventOrganizer (idEventOrganizer)
);

CREATE TABLE CustomerTicket
(
    idCustomerTicket        INT PRIMARY KEY,
    Customer_idCustomer     INT,
    TicketType_idTicketType INT,
    FOREIGN KEY (Customer_idCustomer) REFERENCES Customer (idCustomer),
    FOREIGN KEY (TicketType_idTicketType) REFERENCES TicketType (idTicketType)
);

SET FOREIGN_KEY_CHECKS = 1;
