package edu.Projects.TicketingSystem.Model.EventComponents;

import edu.Projects.TicketingSystem.Model.DataHandlerInterfaces.DataHandler;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class TicketInfo {
    final int ticketID;
    final int eventID;
    double ticketPrice;
    final String ticketName;
    final String ticketType;
    final LocalDate expirationDate;

    DataHandler dataHandler;

    TicketInfo(DataHandler dataHandler, int ticketID, int eventID,double ticketPrice ,String ticketName, String ticketType, LocalDate expirationDate) {
        this.ticketID = ticketID;
        this.eventID = eventID;
        this.ticketPrice = ticketPrice;
        this.ticketName = ticketName;
        this.ticketType = ticketType;
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketInfo that = (TicketInfo) o;
        return ticketID == that.ticketID && eventID == that.eventID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketID, eventID);
    }

    public boolean isValidForNow() {
        LocalDate now = LocalDate.now();
        return now.isBefore(expirationDate);
    }
}
