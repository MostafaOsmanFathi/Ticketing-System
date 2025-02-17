package edu.Projects.TicketingSystem.Model.EventComponents;

import edu.Projects.TicketingSystem.Model.DataHandlerInterfaces.DataHandler;

import java.time.LocalDate;

public class CustomerTicket extends TicketInfo {
    int CustomerID;

    boolean isRefundable;
    double refundCost;

    CustomerTicket(DataHandler dataHandler, int ticketID, int eventID, double ticketPrice, String ticketName, String ticketType, LocalDate expirationDate) {
        super(dataHandler, ticketID, eventID, ticketPrice, ticketName, ticketType, expirationDate);
        this.isRefundable = false;
        this.refundCost = ticketPrice;
    }

    CustomerTicket(DataHandler dataHandler, int ticketID, int eventID, double ticketPrice, String ticketName, String ticketType, LocalDate expirationDate, boolean isRefundable, double refundCost) {
        super(dataHandler, ticketID, eventID, ticketPrice, ticketName, ticketType, expirationDate);
        this.isRefundable = isRefundable;
        this.refundCost = refundCost;
    }
}
