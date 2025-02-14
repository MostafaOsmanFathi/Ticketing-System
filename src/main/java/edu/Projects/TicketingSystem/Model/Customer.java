package edu.Projects.TicketingSystem.Model;

import edu.Projects.TicketingSystem.Model.DataHandlerInterfaces.AccountHandler;
import edu.Projects.TicketingSystem.Model.EventComponents.Ticket;

import java.util.ArrayList;
import java.util.List;

public final class Customer extends Account {
    List<Ticket> tickets;

    private Customer(AccountHandler accountDataHandler, int id, String name, String password, String email, String phone, Double balance) {
        super(accountDataHandler, id, name, password, email, phone, balance);
        this.tickets = new ArrayList<>();
    }
}
