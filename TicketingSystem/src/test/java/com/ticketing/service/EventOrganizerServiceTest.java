package com.ticketing.service;

import com.ticketing.enums.AccountType;
import com.ticketing.model.Account;
import com.ticketing.model.Event;
import com.ticketing.model.EventOrganizer;
import com.ticketing.model.TicketType;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.repository.MySqlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventOrganizerServiceTest {
    EventOrganizerService eventOrganizerService;
    AccountService accountService;
    DatabaseRepository databaseRepository;

    @BeforeEach
    void setUp() {
        eventOrganizerService = EventOrganizerService.getInstance(MySqlRepository.getInstance());
        databaseRepository = MySqlRepository.getInstance();
        databaseRepository.clearDatabase();
        accountService = new AccountService(MySqlRepository.getInstance());
        accountService.register(AccountType.EventOrganizer, 1, "test", "test@example.com", "password123");
    }

    @Test
    void getInstance() {
        EventOrganizerService instance1 = EventOrganizerService.getInstance(MySqlRepository.getInstance());
        EventOrganizerService instance2 = EventOrganizerService.getInstance(MySqlRepository.getInstance());
        assertSame(instance1, instance2, "getInstance should return the same instance (Singleton pattern)");
    }

    @Test
    void addEvent() {
        accountService.login("test@example.com", "password123");
        if (accountService.getAccount() instanceof EventOrganizer eventOrganizer) {
            Event event = new Event(1, eventOrganizer.getEventOrganizerId(), "test", "fps", "event fps");
            assertTrue(eventOrganizerService.addEvent(eventOrganizer, event));
        } else {
            fail("account is not EventOrganizer");
        }
    }

    @Test
    void addTicketTypeToEvent() {
        accountService.login("test@example.com", "password123");
        if (accountService.getAccount() instanceof EventOrganizer eventOrganizer) {
            Event event = new Event(1, eventOrganizer.getEventOrganizerId(), "test", "fps", "event fps");
            assertTrue(eventOrganizerService.addEvent(eventOrganizer, event));
            TicketType ticketType = new TicketType(1, 1, 1, 100, 3, "VIP", "Challenger");
            assertTrue(eventOrganizerService.addTicketTypeToEvent(event, ticketType));
        } else {
            fail("account is not EventOrganizer");
        }
    }

    @Test
    void getTicketTypeFromEvent() {
        accountService.login("test@example.com", "password123");
        if (accountService.getAccount() instanceof EventOrganizer eventOrganizer) {
            Event event = new Event(1, eventOrganizer.getEventOrganizerId(), "test", "fps", "event fps");
            assertTrue(eventOrganizerService.addEvent(eventOrganizer, event));
            TicketType ticketType = new TicketType(1, 1, 1, 100, 3, "VIP", "Challenger");
            assertTrue(eventOrganizerService.addTicketTypeToEvent(event, ticketType));

            TicketType ticketType2 = eventOrganizerService.getTicketTypeFromEvent(event, 1);
            assertNotNull(ticketType2);
        } else {
            fail("account is not EventOrganizer");
        }
    }
}