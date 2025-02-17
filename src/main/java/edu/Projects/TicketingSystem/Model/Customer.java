package edu.Projects.TicketingSystem.Model;

import edu.Projects.TicketingSystem.Model.DataHandlerInterfaces.DataHandler;
import edu.Projects.TicketingSystem.Model.EventComponents.CustomerTicket;

import java.util.ArrayList;
import java.util.List;

public final class Customer extends Account {
    final List<CustomerTicket> tickets;

    private Customer(DataHandler accountDataHandler, int id, String name, String password, String email, String phone, Double balance) {
        super(accountDataHandler, id, name, password, email, phone, balance);
        this.tickets = new ArrayList<>();
    }


    //TODO     boolean buyTicket(int eventId, int ticketID) {
    boolean buyTicket(int eventId, int ticketID) {
        return false;
    }

    //TODO     boolean refundTicket(int eventId, int ticketID) {
    boolean refundTicket(int eventId, int ticketID) {
        return false;
    }
}
