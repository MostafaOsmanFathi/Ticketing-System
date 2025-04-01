package com.ticketing.view;

import com.ticketing.model.Event;
import com.ticketing.model.EventOrganizer;
import com.ticketing.model.TicketType;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.service.AccountService;
import com.ticketing.service.EventOrganizerService;

import javax.swing.*;
import java.awt.*;

public class CreateTicketTypePage extends BaseWindow {
    private Event event;
    private AccountService accountService;
    private DatabaseRepository databaseRepository;
    private EventOrganizerService eventOrganizerService;

    public CreateTicketTypePage(Event event, AccountService accountService, DatabaseRepository databaseRepository) {
        super("Create Ticket");
        this.event = event;
        this.accountService = accountService;
        this.databaseRepository = databaseRepository;
        this.eventOrganizerService = EventOrganizerService.getInstance(databaseRepository);
        // Get main panel
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Ticket Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Ticket Name:"), gbc);
        gbc.gridx = 1;
        JTextField ticketNameField = new JTextField(15);
        formPanel.add(ticketNameField, gbc);

        // Ticket Type
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Ticket Type:"), gbc);
        gbc.gridx = 1;
        JTextField ticketTypeField = new JTextField(15);
        formPanel.add(ticketTypeField, gbc);

        // Ticket Price
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Ticket Price:"), gbc);
        gbc.gridx = 1;
        JTextField ticketPriceField = new JTextField(15);
        formPanel.add(ticketPriceField, gbc);

        // Number of Tickets
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Number of Tickets:"), gbc);
        gbc.gridx = 1;
        JTextField numberOfTicketsField = new JTextField(15);
        formPanel.add(numberOfTicketsField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Create");
        JButton backButton = new JButton("Back");
        buttonPanel.add(createButton);
        buttonPanel.add(backButton);

        // Add panels to main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Back button action
        backButton.addActionListener(e -> {
            dispose();
            new ShowEventPage(event, accountService, databaseRepository);
        });
        createButton.addActionListener(e -> {
            EventOrganizer eventOrganizer = (EventOrganizer) accountService.getAccount();

            TicketType ticketType = new TicketType(event.getEventId(), eventOrganizer.getEventOrganizerId(), 0, Double.parseDouble(ticketPriceField.getText()), Integer.parseInt(numberOfTicketsField.getText()), ticketTypeField.getText(), ticketNameField.getText());;

            if (eventOrganizerService.addTicketTypeToEvent(event, ticketType)) {
                JOptionPane.showMessageDialog(this, "Ticket Type Created");
                dispose();
                new ShowEventPage(event, accountService, databaseRepository);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ticket Creation Failed");
            }

        });

        // Refresh UI
        revalidate();
        repaint();
    }
}
