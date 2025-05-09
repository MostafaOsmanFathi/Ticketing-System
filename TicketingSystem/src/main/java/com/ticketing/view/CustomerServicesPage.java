package com.ticketing.view;

import com.ticketing.repository.DatabaseRepository;
import com.ticketing.service.AccountService;
import com.ticketing.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class CustomerServicesPage extends BaseWindow {
    AccountService accountService;
    CustomerService customerService;
    DatabaseRepository databaseRepository;

    public CustomerServicesPage(DatabaseRepository databaseRepository, AccountService accountService) {
        super("Customer Services");
        this.databaseRepository = databaseRepository;
        this.accountService = accountService;
        this.customerService = CustomerService.getInstance(databaseRepository);

        // Get main panel from BaseWindow
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Customer Services", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton accountServicesButton = new JButton("Account Services");
        accountServicesButton.setFont(new Font("Arial", Font.PLAIN, 18));
        buttonPanel.add(accountServicesButton);


        // Browse Events Button
        JButton browseMyTicketsButton = new JButton("Browse My Tickets");
        browseMyTicketsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        buttonPanel.add(browseMyTicketsButton);

        // Browse Tickets Button
        JButton browseTicketsButton = new JButton("Browse Tickets");
        browseTicketsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        buttonPanel.add(browseTicketsButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add components to main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        accountServicesButton.addActionListener(e -> {
            dispose();
            new AccountServicesPage(databaseRepository, accountService);
        });
        // Event Listeners
        browseMyTicketsButton.addActionListener(e -> {
            dispose();
            new BrowseMyCustomerTicketsPage(databaseRepository, accountService);
        });

        browseTicketsButton.addActionListener(e -> {
            dispose();
            new BrowseAllTicketTypesPage(databaseRepository, accountService);
        });

        backButton.addActionListener(e -> {
            dispose();
            new MainMenu(databaseRepository);
        });

        // Refresh UI
        revalidate();
        repaint();
    }
}
