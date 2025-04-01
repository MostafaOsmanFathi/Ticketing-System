package com.ticketing.view;

import com.ticketing.model.Event;
import com.ticketing.model.EventOrganizer;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.service.AccountService;
import com.ticketing.service.EventOrganizerService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BrowseEventPage extends BaseWindow {
    private AccountService accountService;
    private DatabaseRepository databaseRepository;
    private EventOrganizerService eventOrganizerService;
    List<Event> accountEvents;

    public BrowseEventPage(AccountService accountService, DatabaseRepository databaseRepository) {
        super("Browse My Events");
        this.accountService = accountService;
        this.databaseRepository = databaseRepository;
        this.eventOrganizerService = EventOrganizerService.getInstance(databaseRepository);

        // Get main panel
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Fetch event names
        List<String> eventNames = new ArrayList<>();

        EventOrganizer eventOrganizer = (EventOrganizer) accountService.getAccount();

        this.accountEvents = databaseRepository.getEventsForEventOrganizer(eventOrganizer.getEventOrganizerId());

        for (var event : this.accountEvents) {
            eventNames.add(event.getEventName() + "           " + event.getEventId());
        }

        String[] eventsArray = eventNames.toArray(new String[0]);

        // Event List
        JList<String> eventList = new JList<>(eventsArray);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (!eventNames.isEmpty()) {
            eventList.setSelectedIndex(0); // Default selection to first item
        }

        JScrollPane scrollPane = new JScrollPane(eventList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton selectButton = new JButton("Select");
        JButton backButton = new JButton("Back");

        buttonPanel.add(selectButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            dispose();
            new EventOrganizerServicesPage(databaseRepository, accountService);
        });

        selectButton.addActionListener(e -> {
            int selectedIndex = eventList.getSelectedIndex();
            if (selectedIndex != -1) {
                dispose();
                new ShowEventPage(accountEvents.get(selectedIndex), accountService, databaseRepository);
            }
        });

        // Refresh UI
        revalidate();
        repaint();
    }
}
