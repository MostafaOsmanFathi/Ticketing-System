package com.ticketing.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerTicket {
    private final int ticketId;
    private final int ticketTypeId;
    private final int customerId;
    private final int eventId;


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
}
