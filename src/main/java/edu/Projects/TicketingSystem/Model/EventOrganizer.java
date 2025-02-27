package edu.Projects.TicketingSystem.Model;

import edu.Projects.TicketingSystem.Model.EventComponents.Event;
import edu.Projects.TicketingSystem.Model.EventComponents.Ticket;

import java.util.ArrayList;
import java.util.List;

public final class EventOrganizer extends Account {
    private final List<Event> events;

    public EventOrganizer(int id, String name, String password, String email, String phone, Double balance) {
        super(id, name, password, email, phone, balance);
        events = new ArrayList<Event>();
    }

    public EventOrganizer(Account account) {
        super(account);
        events = new ArrayList<Event>();

    }

    public List<Event> getEvents() {
        return events;
    }

    public boolean createEvent(int eventID, String eventName, String eventType, String eventDescription) {
        Event event = new Event(eventID, eventName, eventType, eventDescription);
        events.add(event);
        return false;
    }

    public boolean createEvent(Event event) {
        return createEvent(event.getEventID(), event.getEventName(), event.getEventType(), event.getEventDescription());
    }

    public boolean deleteEvent(Event event) {
        int index = events.indexOf(event);
        if (index != -1) {
            events.remove(index);
            return true;
        } else
            return false;
    }

    int getIdxByEventId(int eventID) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventID() == eventID) {
                return i;
            }
        }
        return -1;
    }


    public boolean addTicketForEvent(int eventId, Ticket ticket) {
        int idx = getIdxByEventId(eventId);
        if (idx != -1) {
            Event event = events.get(idx);
            event.addTicket(ticket);
        }
        return false;
    }

}
