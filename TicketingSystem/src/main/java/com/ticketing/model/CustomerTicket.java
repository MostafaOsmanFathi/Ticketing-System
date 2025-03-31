package com.ticketing.model;

public class CustomerTicket {
    private int ticketId;
    private int ticketTypeId;
    private int customerId;
    private int eventId;


    public int getTicketId() {
        return ticketId;
    }

    public CustomerTicket(int ticketId, int customerId, int ticketTypeId, int eventId) {
        this.ticketTypeId = ticketTypeId;
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.eventId = eventId;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
