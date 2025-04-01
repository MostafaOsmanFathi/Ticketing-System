package com.ticketing.view;

import com.ticketing.repository.DatabaseRepository;
import com.ticketing.service.AccountService;
import com.ticketing.service.EventOrganizerService;

import javax.swing.*;
import java.awt.*;

public class EventOrganizerServicesPage extends BaseWindow {
    AccountService accountService;
    EventOrganizerService eventOrganizerService;
    DatabaseRepository databaseRepository;

    public EventOrganizerServicesPage(DatabaseRepository databaseRepository, AccountService accountService) {
        super("Event Organizer Services");
        this.databaseRepository = databaseRepository;
        this.accountService = accountService;
        this.eventOrganizerService = EventOrganizerService.getInstance(databaseRepository);

        // Get main panel
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Event Organizer Services", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton accountServicesButton = new JButton("Account Services");
        JButton createEventButton = new JButton("Create Event");
        JButton browseEventsButton = new JButton("Browse My Events");

        JButton logOutButton = new JButton("Log Out");
        logOutButton.setFont(new Font("Arial", Font.PLAIN, 18));


        buttonPanel.add(accountServicesButton);
        buttonPanel.add(createEventButton);
        buttonPanel.add(browseEventsButton);
        buttonPanel.add(logOutButton);

        // Add components to main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        accountServicesButton.addActionListener(e -> {
            dispose();
            new AccountServicesPage(databaseRepository, accountService);
        });

        createEventButton.addActionListener(e -> {
            dispose();
            new CreateEventPage(accountService, databaseRepository);
        });

        browseEventsButton.addActionListener(e -> {
            dispose();
            new BrowseEventPage(accountService, databaseRepository);
        });

        logOutButton.addActionListener(e -> {
            dispose();
            accountService.logout();
            new LoginPage(databaseRepository);
        });
        // Refresh UI
        revalidate();
        repaint();
    }
}