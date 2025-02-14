package edu.Projects.TicketingSystem.Model.EventComponents;

import java.util.ArrayList;
import java.util.List;

public class Event {
    String eventName;
    String eventType;
    String eventDescription;
    List<Ticket> tickets;

    Event(String eventName, String eventType, String eventDescription) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.tickets = new ArrayList<>();
    }

    boolean addTicket(Ticket ticket) {
        return this.tickets.add(ticket);
    }
}
