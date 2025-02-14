package edu.Projects.TicketingSystem.Model.EventComponents;

public class TicketInfo {
    final int ticketID;
    final int eventID;
    final String ticketName;
    final String ticketType;
    TicketInfo(int ticketID, int eventID, String ticketName, String ticketType) {
        this.ticketID = ticketID;
        this.eventID = eventID;
        this.ticketName = ticketName;
        this.ticketType = ticketType;
    }
}
