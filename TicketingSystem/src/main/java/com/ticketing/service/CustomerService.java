package com.ticketing.service;

import com.ticketing.model.Customer;
import com.ticketing.model.CustomerTicket;
import com.ticketing.model.TicketType;
import com.ticketing.repository.AccountRepository;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.repository.TicketingRepository;

public class CustomerService {

    private static TicketingRepository ticketingRepository;
    private static AccountRepository accountRepository;
    private static CustomerService instance;

    private CustomerService(DatabaseRepository databaseRepository) {
        ticketingRepository = databaseRepository;
        accountRepository = databaseRepository;
    }

    public static CustomerService getInstance(DatabaseRepository databaseRepository) {
        if (instance == null) {
            instance = new CustomerService(databaseRepository);
        }
        return instance;
    }


    boolean buyTicket(Customer customer, TicketType ticket) {
        if (customer.withdraw(ticket.getTicketPrice()) &&
                accountRepository.withdraw(customer, ticket.getTicketPrice()) &&
                accountRepository.depositToAccountByAccountId(ticket.getEventOrgnizerId(), ticket.getTicketPrice())) {

            CustomerTicket newTicket = ticket.generateCustomerTicket(customer.getAccountId());

            return ticketingRepository.addTicketToCustomer(customer, newTicket)
                    && customer.addCustomerTicket(newTicket)
                    && ticket.decreaseNumberOfTicket()
                    && decreaseNumberOfTicket(ticket);
        }
        return false;
    }

    boolean decreaseNumberOfTicket(TicketType ticket) {
        return ticketingRepository.decreaseTicketType(ticket.getEventId(), ticket.getTicketTypeId());
    }

}
