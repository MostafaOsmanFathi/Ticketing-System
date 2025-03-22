package com.ticketing.util;

public class IdGenerator {
    private int IdCounter;

    public IdGenerator() {
        this.IdCounter = 0;
    }

    public int getTicketId() {
        IdCounter++;
        return IdCounter;
    }
}
