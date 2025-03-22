package com.ticketing.service;

import com.ticketing.model.Account;
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

    private CustomerService() {
        ticketingRepository = DatabaseRepository.getInstance();
        accountRepository = DatabaseRepository.getInstance();
    }

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }


    boolean buyTicket(Account account, TicketType ticket) {
        if (account instanceof Customer customer) {
            if (customer.withdraw(ticket.getTicketPrice()) &&
                    accountRepository.depositToAccountByAccountId(ticket.getEventOrgnizerId(), ticket.getTicketPrice())) {

                CustomerTicket newTicket = ticket.generateCustomerTicket(account.getAccountId());

                return ticketingRepository.addTicketToCustomer(customer, newTicket)
                        && customer.addCustomerTicket(newTicket);
            }
        }
        return false;
    }

}
