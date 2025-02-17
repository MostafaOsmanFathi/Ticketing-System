package edu.Projects.TicketingSystem.Model.EventComponents;

import edu.Projects.TicketingSystem.Model.DataHandlerInterfaces.DataHandler;

import java.time.LocalDate;

public class Ticket extends TicketInfo {
    final boolean isTicketRefundable;
    final double refundRate;
    int numberOfTickets;

    Ticket(DataHandler dataHandler, int ticketID, int eventID, double ticketPrice, String ticketName, String ticketType, LocalDate date, boolean isTicketRefundable, int count, double refundRate) {
        super(dataHandler, ticketID, eventID, ticketPrice, ticketName, ticketType, date);
        this.numberOfTickets = count;
        this.isTicketRefundable = isTicketRefundable;
        this.refundRate = refundRate;
    }

    public boolean isTicketRefundable() {
        return isTicketRefundable;
    }

    public double getRefundRate() {
        return refundRate;
    }

    public double getRefundCost() {
        return refundRate * ticketPrice;
    }

    int getNumberOfTickets() {
        return numberOfTickets;
    }

    boolean increaseCount() {
        numberOfTickets++;
        return true;
    }

    boolean decreaseCount() {
        numberOfTickets--;
        return true;
    }
}
