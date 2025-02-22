package edu.Projects.TicketingSystem.Model;

import edu.Projects.TicketingSystem.Model.EventComponents.CustomerTicket;
import edu.Projects.TicketingSystem.Model.EventComponents.Ticket;

import java.util.ArrayList;
import java.util.List;

public final class Customer extends Account {
    public List<CustomerTicket> getTickets() {
        return tickets;
    }

    final List<CustomerTicket> tickets;

    public Customer(int id, String name, String password, String email, String phone, Double balance) {
        super(id, name, password, email, phone, balance);
        this.tickets = new ArrayList<>();
    }

    public Customer(Account account) {
        super(account);
        this.tickets = new ArrayList<>();
    }

    public boolean buyTicket(CustomerTicket ticket) {
        if (this.withdraw(ticket.getTicketPrice())) {
            tickets.add(ticket);
            return true;
        } else
            return false;
    }

    public boolean refundTicket(CustomerTicket ticket) {
        if (tickets.contains(ticket)) {
            tickets.remove(ticket);
            return true;
        } else {
            return false;
        }
    }
}
