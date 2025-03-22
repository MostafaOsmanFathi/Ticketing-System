package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Account {
    private final List<CustomerTicket> customerTickets;

    public Customer(int accountId, String name, String email, String password) {
        super(accountId, name, email, password);
        this.customerTickets = new ArrayList<CustomerTicket>();
    }

    public boolean buyTicket(TicketType ticketType) {
        if (this.withdraw(ticketType.getTicketPrice())) {
            CustomerTicket newTicket = ticketType.generateCustomerTicket(getAccountId());
            customerTickets.add(newTicket);
            return true;
        }
        return false;
    }

}
