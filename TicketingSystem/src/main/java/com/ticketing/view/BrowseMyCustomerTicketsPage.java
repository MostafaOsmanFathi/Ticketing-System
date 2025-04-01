package com.ticketing.view;

import com.ticketing.model.Customer;
import com.ticketing.model.CustomerTicket;
import com.ticketing.model.TicketType;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.service.AccountService;
import com.ticketing.service.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BrowseMyCustomerTicketsPage extends BaseWindow {
    Customer customer;
    CustomerService customerService;
    DatabaseRepository databaseRepository;
    AccountService accountService;

    public BrowseMyCustomerTicketsPage(DatabaseRepository databaseRepository, AccountService accountService) {
        super("My Tickets");
        this.databaseRepository = databaseRepository;
        this.accountService = accountService;
        this.customerService = CustomerService.getInstance(databaseRepository);
        this.customer = (Customer) accountService.getAccount();

        // Get main panel
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Fetch tickets
        List<CustomerTicket> customerTickets = databaseRepository.getCustomerTicketsByCustomerId(customer.getCustomerId());
        List<String> customerTicketsString = new ArrayList<>();
        for (CustomerTicket customerTicket : customerTickets) {
            TicketType ticketType = databaseRepository.getTicketType(customerTicket.getEventId(), customerTicket.getTicketTypeId());
            customerTicketsString.add(ticketType.getTicketName() + " - $" + ticketType.getTicketPrice());
        }

        String[] ticketsArray = customerTicketsString.toArray(new String[0]);

        // Ticket List
        JList<String> ticketList = new JList<>(ticketsArray);
        ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (!customerTicketsString.isEmpty()) {
            ticketList.setSelectedIndex(0); // Default selection to first item
        }

        JScrollPane scrollPane = new JScrollPane(ticketList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            dispose();
            new CustomerServicesPage(databaseRepository, accountService);
        });

        // Refresh UI
        revalidate();
        repaint();
    }
}
