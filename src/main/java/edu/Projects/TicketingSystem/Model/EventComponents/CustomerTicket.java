package edu.Projects.TicketingSystem.Model.EventComponents;


import java.time.LocalDate;

public class CustomerTicket extends TicketInfo {
    int customerID;
    boolean isRefundable;
    double refundCost;
    TicketStatus status;

    public CustomerTicket(int customerID, int ticketID, int eventID, double ticketPrice, String ticketName, String ticketType, LocalDate expirationDate) {
        super(ticketID, eventID, ticketPrice, ticketName, ticketType, expirationDate);
        this.customerID = customerID;
        this.isRefundable = false;
        this.refundCost = ticketPrice;
        this.status = TicketStatus.NotConfirmed;
    }

    public CustomerTicket(int customerID, int ticketID, int eventID, double ticketPrice, String ticketName, String ticketType, LocalDate expirationDate, boolean isRefundable, double refundCost) {
        super(ticketID, eventID, ticketPrice, ticketName, ticketType, expirationDate);
        this.customerID = customerID;
        this.isRefundable = isRefundable;
        this.refundCost = refundCost;
        this.status = TicketStatus.NotConfirmed;
    }

    public CustomerTicket(int id, Ticket ticket) {
        super(ticket.ticketID, ticket.eventID, ticket.ticketPrice, ticket.ticketName, ticket.ticketType, ticket.expirationDate);
        this.isRefundable = ticket.isTicketRefundable;
        this.refundCost = ticket.getRefundCost();
        this.customerID = id;
    }

    public boolean confirmTicket() {
        if (isValidForNow()) {
            this.status = TicketStatus.Confirmed;
            return true;
        }
        this.status = TicketStatus.NOtValid;
        return false;
    }
}
