PRAGMA
    foreign_keys = ON;

DROP TABLE IF EXISTS CustomerTicket;
DROP TABLE IF EXISTS TicketType;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS EventOrganizer;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Account;

CREATE TABLE IF NOT EXISTS Account
(
    idAccount
        INTEGER
        PRIMARY KEY AUTOINCREMENT,
    name
        TEXT,
    email
        TEXT
        UNIQUE
        NOT NULL,
    password
        TEXT
        NOT NULL,
    balance
        REAL
        DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS Customer
(
    idCustomer
        INTEGER
        PRIMARY KEY AUTOINCREMENT,
    Account_idAccount
        INTEGER
        NOT NULL,
    FOREIGN KEY
        (Account_idAccount) REFERENCES Account
        (idAccount)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS EventOrganizer
(
    idEventOrganizer
        INTEGER
        PRIMARY KEY AUTOINCREMENT,
    Account_idAccount
        INTEGER
        NOT NULL,
    FOREIGN KEY
        (Account_idAccount) REFERENCES Account
        (idAccount)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Event
(
    idEvent
        INTEGER
        PRIMARY KEY AUTOINCREMENT,
    eventName
        TEXT
        NOT NULL,
    eventType
        TEXT,
    eventDescription
        TEXT,
    EventOrganizer_idEventOrganizer
        INTEGER
        NOT NULL,
    FOREIGN KEY
        (EventOrganizer_idEventOrganizer) REFERENCES EventOrganizer
        (idEventOrganizer)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS TicketType
(
    idTicketType
        INTEGER
        PRIMARY KEY AUTOINCREMENT,
    ticketPrice
        REAL
        NOT NULL,
    ticketName
        TEXT
        NOT NULL,
    expirationDate
        TEXT,
    numberOfTickets
        INTEGER
        NOT NULL,
    Event_idEvent
        INTEGER
        NOT NULL,
    EventOrganizer_id
        INTEGER
        NOT NULL,
    FOREIGN KEY
        (Event_idEvent) REFERENCES Event
        (idEvent)
        ON DELETE CASCADE,
    FOREIGN KEY
        (EventOrganizer_id) REFERENCES EventOrganizer
        (idEventOrganizer)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS CustomerTicket
(
    idCustomerTicket
        INTEGER
        PRIMARY KEY AUTOINCREMENT,
    Customer_idCustomer
        INTEGER
        NOT NULL,
    TicketType_idTicketType
        INTEGER
        NOT NULL,
    FOREIGN KEY
        (Customer_idCustomer) REFERENCES Customer
        (idCustomer)
        ON DELETE CASCADE,
    FOREIGN KEY
        (TicketType_idTicketType) REFERENCES TicketType
        (idTicketType)
        ON DELETE CASCADE
);
