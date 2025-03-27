-- Ensure the correct database is selected
USE
    TicketingSystem;


-- Insert initial Accounts
INSERT INTO ticketingsystem.Account (account.idAccount, account.name, account.email, account.password, account.balance)
VALUES (1, 'John Doe', 'john.doe@example.com', 'password123', 100.0),
       (2, 'Jane Smith', 'jane.smith@example.com', 'securePass456', 250.5),
       (3, 'Alice Johnson', 'alice.johnson@example.com', 'alicePass789', 500.0),
       (4, 'Bob Brown', 'bob.brown@example.com', 'bobPass321', 150.0);

-- Insert Customers (Linked to Account)
INSERT INTO Customer (customer.idCustomer, customer.Account_idAccount)
VALUES (1, 1), -- John Doe as Customer
       (2, 2);
-- Jane Smith as Customer

-- Insert Event Organizers (Linked to Account)
INSERT INTO EventOrganizer (eventorganizer.idEventOrganizer, eventorganizer.Account_idAccount)
VALUES (1, 3), -- Alice Johnson as Organizer
       (2, 4);
-- Bob Brown as Organizer

-- Insert Events (Linked to Event Organizer)
INSERT INTO Event (event.idEvent, event.eventName, event.eventType, event.eventDescription,
                   event.EventOrganizer_idEventOrganizer)
VALUES (1, 'Rock Concert', 'Music', 'An exciting rock concert featuring top bands.', 1), -- Alice Johnson
       (2, 'Tech Conference', 'Technology', 'Annual tech conference with expert speakers.', 2);
-- Bob Brown

-- Insert Ticket Types (Linked to Event & Organizer)
INSERT INTO TicketType (tickettype.idTicketType, tickettype.ticketPrice, tickettype.ticketName,
                        tickettype.expirationDate, tickettype.numberOfTickets, tickettype.Event_idEvent,
                        tickettype.EventOrganizer_id)
VALUES (1, 50.0, 'General Admission', '2025-12-31', 100, 1, 1), -- Rock Concert - Alice Johnson
       (2, 100.0, 'VIP Pass', '2025-12-31', 50, 1, 1),          -- Rock Concert - Alice Johnson
       (3, 200.0, 'Full Access', '2025-11-15', 200, 2, 2),      -- Tech Conference - Bob Brown
       (4, 75.0, 'Student Ticket', '2025-11-15', 150, 2, 2);
-- Tech Conference - Bob Brown

-- Insert Customer Tickets (Linking Customers with Ticket Types)
INSERT INTO CustomerTicket (customerticket.idCustomerTicket, customerticket.Customer_idCustomer,
                            customerticket.TicketType_idTicketType)
VALUES (1, 1, 1), -- John Doe buys General Admission for Rock Concert
       (2, 1, 2), -- John Doe buys VIP Pass for Rock Concert
       (3, 2, 3), -- Jane Smith buys Full Access for Tech Conference
       (4, 2, 4); -- Jane Smith buys Student Ticket for Tech Conference
