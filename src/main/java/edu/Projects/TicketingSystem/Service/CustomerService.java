package edu.Projects.TicketingSystem.Service;

import edu.Projects.TicketingSystem.Model.EventComponents.Ticket;
import edu.Projects.TicketingSystem.Service.DataHandlerInterfaces.DataHandler;

import java.util.List;

public class CustomerService {

    static CustomerService customerService = null;
    static DataHandler dataHandler = null;

    private CustomerService() {
    }

    public static CustomerService getCustomerService(DataHandler dataHandlerPram) {
        if (customerService == null) {
            customerService = new CustomerService();
            dataHandler = dataHandlerPram;
        }
        return customerService;
    }

    public List<Ticket> getAllAvlibleTickets() {
        //TODO Show All Tickets
        return null;
    }

    public boolean buyTicket(Ticket ticket) {
        //TODO buy Ticket
        return false;
    }

    public boolean refundTicket(Ticket ticket) {
        //TODO refund tickets
        return false;
    }

}
