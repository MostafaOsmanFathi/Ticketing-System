package edu.Projects.TicketingSystem.Model.EventComponents;

public class Ticket extends TicketInfo {
    int count;

    Ticket(int ticketID, int eventID, String ticketName, String ticketType) {
        super(ticketID, eventID, ticketName, ticketType);
    }
    int getCount() {
        return count;
    }
    boolean increaseCount() {
        count++;
        return true;
    }
    boolean decreaseCount() {
        count--;
        return true;
    }
}
