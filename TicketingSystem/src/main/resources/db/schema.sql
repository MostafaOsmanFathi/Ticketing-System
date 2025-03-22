DROP DATABASE IF EXISTS TicketingSystem;

CREATE DATABASE TicketingSystem;

USE TicketingSystem;

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
    eventDescription                VARCHAR(45),
    EventOrganizer_idEventOrganizer INT,
    FOREIGN KEY (EventOrganizer_idEventOrganizer) REFERENCES EventOrganizer (idEventOrganizer)
);

CREATE TABLE TicketType
(
    idTicketType  INT PRIMARY KEY,
    Event_idEvent INT,
    FOREIGN KEY (Event_idEvent) REFERENCES Event (idEvent)
);

CREATE TABLE TicketInfo
(
    idTicketInfo            INT PRIMARY KEY,
    ticketPrice             DOUBLE,
    ticketName              VARCHAR(45),
    expirationDate          VARCHAR(45),
    TicketType_idTicketType INT,
    FOREIGN KEY (TicketType_idTicketType) REFERENCES TicketType (idTicketType)
);

CREATE TABLE CustomerTicket
(
    idCustomerTicket        INT PRIMARY KEY,
    Customer_idCustomer     INT,
    TicketInfo_idTicketInfo INT,
    FOREIGN KEY (Customer_idCustomer) REFERENCES Customer (idCustomer),
    FOREIGN KEY (TicketInfo_idTicketInfo) REFERENCES TicketInfo (idTicketInfo)
);
