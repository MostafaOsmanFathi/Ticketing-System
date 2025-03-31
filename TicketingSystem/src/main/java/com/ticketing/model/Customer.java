package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Account {
    private int customerId;
    private final List<CustomerTicket> customerTickets;

    public Customer(int accountId, int customerId, String name, String email, String password) {
        super(accountId, name, email, password);
        this.customerId = customerId;
        this.customerTickets = new ArrayList<CustomerTicket>();
    }

    public Customer(Account account, int customerId) {
        super(account);
        this.customerId = customerId;
        this.customerTickets = new ArrayList<CustomerTicket>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public boolean addCustomerTicket(CustomerTicket customerTicket) {
        return customerTickets.add(customerTicket);
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
