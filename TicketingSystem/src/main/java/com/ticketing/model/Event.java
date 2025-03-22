package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private String eventName;
    private String eventType;
    private String eventDescription;
    private final List<TicketType> ticketTypes;

    public Event(String eventName, String eventType, String eventDescription) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.ticketTypes = new ArrayList<>();
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
