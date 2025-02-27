package edu.Projects.TicketingSystem.Service;

import edu.Projects.TicketingSystem.Model.Customer;
import edu.Projects.TicketingSystem.Model.EventComponents.CustomerTicket;
import edu.Projects.TicketingSystem.Model.EventComponents.Ticket;
import edu.Projects.TicketingSystem.Service.DataHandlerInterfaces.DataHandler;

import javax.xml.crypto.Data;
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

    public boolean buyTicket(Customer customer, Ticket ticket) {
        CustomerTicket newCustomerTicket = dataHandler.BuyTicket(customer, ticket);
        if (newCustomerTicket == null) {
            return false;
        }
        customer.buyTicket(newCustomerTicket);
        return true;
    }

    public boolean refundTicket(Ticket ticket) {
        //TODO refund tickets
        return false;
    }

}
