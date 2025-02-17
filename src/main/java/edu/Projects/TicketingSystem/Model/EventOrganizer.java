package edu.Projects.TicketingSystem.Model;

import edu.Projects.TicketingSystem.Model.DataHandlerInterfaces.DataHandler;
import edu.Projects.TicketingSystem.Model.EventComponents.Event;

import java.util.ArrayList;
import java.util.List;

public final class EventOrganizer extends Account {
    private final List<Event> events;

    private EventOrganizer(DataHandler accountDataHandler, int id, String name, String password, String email, String phone, Double balance) {
        super(accountDataHandler, id, name, password, email, phone, balance);
        events = new ArrayList<Event>();
    }

    //TODO compelte getEvents
    public List<Event> getEvents() {
        return events;
    }

    //TODO  createEvent
    public boolean createEvent(int eventID, String eventName, String eventType, String eventDescription) {
//        Event event = new Event(accountDataHandler, eventID, eventName, eventType, eventDescription);

        return false;
    }

    //TODO public boolean deleteEvent(Event event) {
    public boolean deleteEvent(Event event) {
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

    //TODO public boolean addTicketForEvent(int eventId) {
    public boolean addTicketForEvent(int eventId) {
        return false;
    }

}
