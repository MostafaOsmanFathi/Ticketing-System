package edu.Projects.TicketingSystem.Model.EventComponents;

public class CustomerTicket extends TicketInfo {
    int CustomerID;

    CustomerTicket(int ticketID, int eventID, String ticketName, String ticketType) {
        super(ticketID, eventID, ticketName, ticketType);
    }
}
