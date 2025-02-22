package edu.Projects.TicketingSystem.Model.EventComponents;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Event {
    int eventID;
    int numberOfConfirmedTickets;
    String eventName;
    String eventType;
    String eventDescription;
    List<Ticket> tickets;


    public Event(int eventID, String eventName, String eventType, String eventDescription) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.tickets = new ArrayList<>();
        this.numberOfConfirmedTickets = 0;
    }

    public boolean addTicket(Ticket ticket) {
        return this.tickets.add(ticket);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventID == event.eventID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(eventID);
    }

    public int getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public boolean confirmTicket(CustomerTicket ticket) {
        if (ticket.confirmTicket()) {
            this.numberOfConfirmedTickets++;
        }
        return false;
    }

    public Ticket getTicketByTicketId(int ticketID) {

        for (Ticket ticket : tickets) {
            if (ticket.getTicketID() == ticketID) {
                return ticket;
            }
        }
        return null;
    }

}
