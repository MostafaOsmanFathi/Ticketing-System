package edu.Projects.TicketingSystem.Model;

import edu.Projects.TicketingSystem.Model.DataHandlerInterfaces.AccountHandler;
import edu.Projects.TicketingSystem.Model.EventComponents.Event;

import java.util.ArrayList;
import java.util.List;

public final class EventOrganizer extends Account {
    private final List<Event> events;

    private EventOrganizer(AccountHandler accountDataHandler, int id, String name, String password, String email, String phone, Double balance) {
        super(accountDataHandler, id, name, password, email, phone, balance);
        events = new ArrayList<Event>();
    }

    public List<Event> getEvents() {
        return events;
    }
}
