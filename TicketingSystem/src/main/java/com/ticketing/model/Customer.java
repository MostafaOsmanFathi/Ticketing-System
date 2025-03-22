package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Account {
    private final List<CustomerTicket> customerTickets;

    public Customer(int accountId, String name, String email, String password) {
        super(accountId, name, email, password);
        this.customerTickets = new ArrayList<CustomerTicket>();
    }

    public boolean addCustomerTicket(CustomerTicket customerTicket) {
        return customerTickets.add(customerTicket);
    }

}
