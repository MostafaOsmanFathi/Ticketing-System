package com.ticketing.view;

import com.ticketing.model.Event;
import com.ticketing.model.TicketType;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.service.AccountService;
import com.ticketing.service.EventOrganizerService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShowEventPage extends BaseWindow {
    private Event event;
    private EventOrganizerService eventOrganizerService;
    private DatabaseRepository databaseRepository;
    private AccountService accountService;

    public ShowEventPage(Event event, AccountService accountService, DatabaseRepository databaseRepository) {
        super("Event Details - " + event.getEventName());
        this.event = event;
        this.accountService = accountService;
        this.databaseRepository = databaseRepository;
        this.eventOrganizerService = EventOrganizerService.getInstance(databaseRepository);

        // Get main panel
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Event Details Panel
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Event Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        // Event Name
        gbc.gridy = 0;
        detailsPanel.add(new JLabel("Event Name:"), gbc);
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(event.getEventName()), gbc);

        // Event Type
        gbc.gridx = 0;
        gbc.gridy = 1;
        detailsPanel.add(new JLabel("Event Type:"), gbc);
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(event.getEventType()), gbc);

        // Event Description
        gbc.gridx = 0;
        gbc.gridy = 2;
        detailsPanel.add(new JLabel("Event Description:"), gbc);
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(event.getEventDescription()), gbc);

        mainPanel.add(detailsPanel, BorderLayout.NORTH);

        // Ticket Types Panel
        JPanel ticketPanel = new JPanel(new BorderLayout());
        ticketPanel.setBorder(BorderFactory.createTitledBorder("Ticket Types"));

        List<TicketType> ticketTypes = databaseRepository.getTicketTypesByEventId(event.getEventId());

        List<String> ticketTypesNames = new ArrayList<String>();

        for (TicketType ticketType : ticketTypes) {
            ticketTypesNames.add(ticketType.getTicketName() + "   " + ticketType.getNumberOfTickets() + "   " + ticketType.getTicketPrice());
        }

        JList<String> ticketList = new JList<>(ticketTypesNames.toArray(new String[0]));
        ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(ticketList);

        ticketPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(ticketPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);

        JButton createTicketTypeButton = new JButton("Create Ticket Type");
        buttonPanel.add(createTicketTypeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            dispose();
            new BrowseEventPage(accountService, databaseRepository);
        });

        createTicketTypeButton.addActionListener(e -> {
            dispose();
            new CreateTicketTypePage(event, accountService, databaseRepository);
        });
        // Refresh UI
        revalidate();
        repaint();
    }
}
