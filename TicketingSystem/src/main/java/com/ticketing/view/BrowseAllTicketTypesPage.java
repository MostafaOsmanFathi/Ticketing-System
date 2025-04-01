package com.ticketing.view;

import com.ticketing.model.Customer;
import com.ticketing.model.TicketType;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.service.AccountService;
import com.ticketing.service.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BrowseAllTicketTypesPage extends BaseWindow {
    Customer customer;
    CustomerService customerService;
    DatabaseRepository databaseRepository;
    AccountService accountService;

    public BrowseAllTicketTypesPage(DatabaseRepository databaseRepository, AccountService accountService) {
        super("Available Ticket Types");
        this.databaseRepository = databaseRepository;
        this.accountService = accountService;
        this.customer = (Customer) accountService.getAccount();
        customerService = CustomerService.getInstance(databaseRepository);

        // Fetch all ticket types
        List<TicketType> ticketTypes = databaseRepository.getAllTicketTypes();
        List<String> ticketTypesNames = new ArrayList<>();

        for (TicketType ticketType : ticketTypes) {
            ticketTypesNames.add(ticketType.getTicketName() + " - " + ticketType.getNumberOfTickets() + " tickets available - $" + ticketType.getTicketPrice());
        }

        // UI Components
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        String[] ticketsArray = ticketTypesNames.toArray(new String[0]);
        JList<String> ticketList = new JList<>(ticketsArray);
        ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (!ticketTypesNames.isEmpty()) {
            ticketList.setSelectedIndex(0);
        }
        JScrollPane scrollPane = new JScrollPane(ticketList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(e -> {
            int selectedIndex = ticketList.getSelectedIndex();
            if (selectedIndex != -1) {
                if (customerService.buyTicket(customer, ticketTypes.get(selectedIndex))) {
                    JOptionPane.showMessageDialog(mainPanel, "Ticket bought successfully!");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Ticket could not be bought!");
                }
            }
        });
        buttonPanel.add(buyButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new CustomerServicesPage(databaseRepository, accountService);
        });
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }
}
