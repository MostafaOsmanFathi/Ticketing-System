package com.ticketing.model;

import com.ticketing.util.IdGenerator;

public class TicketType {
    private final int eventId;
    private final int ticketTypeId;
    private final int eventOrgnizerId;
    private final IdGenerator idGenerator;
    private double ticketPrice;
    private int numberOfTickets;
    private String ticketType;
    private String ticketName;


    public TicketType(int eventId, int eventOrgnizerId, int ticketTypeId, double ticketPrice, int numberOfTickets, String ticketType, String ticketName) {
        this.eventId = eventId;
        this.ticketTypeId = ticketTypeId;
        this.ticketPrice = ticketPrice;
        this.numberOfTickets = Math.max(numberOfTickets, 0);
        this.ticketType = ticketType;
        this.ticketName = ticketName;
        this.idGenerator = new IdGenerator();
        this.eventOrgnizerId = eventOrgnizerId;
    }

    public CustomerTicket generateCustomerTicket(int customerId) {
        if (this.numberOfTickets == 0) {
            throw new IllegalStateException("There no more tickets");
        }
        decreaseNumberOfTicket();
        return new CustomerTicket(idGenerator.getNextId(), customerId, ticketTypeId, eventId);
    }

    public int getEventOrgnizerId() {
        return eventOrgnizerId;
    }

    public boolean increaseNumberOfTicket() {
        numberOfTickets++;
        return true;
    }

    public boolean decreaseNumberOfTicket() {
        numberOfTickets--;
        return true;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getTicketName() {
        return ticketName;
    }


    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }


    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getEventId() {
        return eventId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }
}
