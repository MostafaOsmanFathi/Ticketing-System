package edu.Projects.TicketingSystem.Service.DataHandlerInterfaces;

import edu.Projects.TicketingSystem.Model.Customer;
import edu.Projects.TicketingSystem.Model.EventComponents.CustomerTicket;
import edu.Projects.TicketingSystem.Model.EventComponents.Ticket;

public interface CustomerHandler {
    CustomerTicket BuyTicket(Customer customer, Ticket ticket);
}
