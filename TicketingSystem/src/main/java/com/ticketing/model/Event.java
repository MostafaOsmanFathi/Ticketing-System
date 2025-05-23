package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private  int eventId;
    private  int eventOrganizerId;
    private String eventName;
    private String eventType;
    private String eventDescription;
    private final List<TicketType> ticketTypes;

    public Event(int eventId, int eventOrganizerId, String eventName, String eventType, String eventDescription) {
        this.eventId = eventId;
        this.eventOrganizerId = eventOrganizerId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.ticketTypes = new ArrayList<>();
    }

    public int getEventOrganizerId() {
        return eventOrganizerId;
    }

    public boolean addTicketType(TicketType ticketType) {
        return ticketTypes.add(ticketType);
    }

    public TicketType getTicketTypes(int ticketTypeId) {
        if (ticketTypeId < 0 || ticketTypeId >= ticketTypes.size()) {
            throw new IndexOutOfBoundsException();
        }
        return ticketTypes.get(ticketTypeId);
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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventOrganizerId(int eventOrganizerId) {
        this.eventOrganizerId = eventOrganizerId;
    }

}
