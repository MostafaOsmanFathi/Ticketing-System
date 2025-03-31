package com.ticketing.view;

import com.ticketing.model.Event;
import com.ticketing.model.EventOrganizer;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.service.AccountService;
import com.ticketing.service.EventOrganizerService;

import javax.swing.*;
import java.awt.*;

public class CreateEventPage extends BaseWindow {
    private AccountService accountService;
    private DatabaseRepository databaseRepository;
    private EventOrganizerService eventOrganizerService;
    private JTextField eventNameField;
    private JTextField eventTypeField;
    private JTextArea eventDescriptionArea;

    public CreateEventPage(AccountService accountService, DatabaseRepository databaseRepository) {
        super("Create Event - Ticketing System");
        this.accountService = accountService;
        this.databaseRepository = databaseRepository;
        this.eventOrganizerService = EventOrganizerService.getInstance(databaseRepository);

        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Event Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Event Name:"), gbc);
        gbc.gridx = 1;
        eventNameField = new JTextField(15);
        formPanel.add(eventNameField, gbc);

        // Event Type
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Event Type:"), gbc);
        gbc.gridx = 1;
        eventTypeField = new JTextField(15);
        formPanel.add(eventTypeField, gbc);

        // Event Description
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Event Description:"), gbc);
        gbc.gridx = 1;
        eventDescriptionArea = new JTextArea(3, 15);
        formPanel.add(new JScrollPane(eventDescriptionArea), gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Create");
        JButton backButton = new JButton("Back");
        buttonPanel.add(createButton);
        buttonPanel.add(backButton);

        // Add components to main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            dispose();
            new EventOrganizerServicesPage(databaseRepository, accountService);
        });

        createButton.addActionListener(e -> {
            Event event = new Event(0, accountService.getAccount().getAccountId(), eventNameField.getText(), eventTypeField.getText(), eventDescriptionArea.getText());
            if (eventOrganizerService.addEvent((EventOrganizer) accountService.getAccount(), event)) {
                JOptionPane.showMessageDialog(null, "Event created!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new EventOrganizerServicesPage(databaseRepository, accountService);
            } else {
                JOptionPane.showMessageDialog(null, "Event creation failed!", "Failed", JOptionPane.ERROR_MESSAGE);
            }

        });
        // Refresh UI
        revalidate();
        repaint();
    }
}
