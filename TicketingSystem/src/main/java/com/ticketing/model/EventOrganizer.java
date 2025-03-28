package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class EventOrganizer extends Account {
    private final List<Event> events;

    private final int eventOrganizerId;

    public EventOrganizer(int accountId, int eventOrganizerId, String name, String email, String password) {
        super(accountId, name, email, password);
        this.eventOrganizerId = eventOrganizerId;
        this.events = new ArrayList<Event>();
    }

    public EventOrganizer(Account account, int eventOrganizerId) {
        super(account);
        this.eventOrganizerId = eventOrganizerId;
        this.events = new ArrayList<Event>();
    }

    public int getEventOrganizerId() {
        return eventOrganizerId;
    }

    public boolean addEvent(Event event) {
        return events.add(event);
    }
}
