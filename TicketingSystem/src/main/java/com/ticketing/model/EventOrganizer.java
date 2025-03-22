package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class EventOrganizer extends Account {
    private final List<Event> events;

    public EventOrganizer(int accountId, String name, String email, String password) {
        super(accountId, name, email, password);
        this.events = new ArrayList<Event>();
    }

    public boolean addEvent(Event event) {
        return events.add(event);
    }
}
