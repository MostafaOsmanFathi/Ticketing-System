package com.ticketing.view;

import com.ticketing.repository.DatabaseRepository;
import com.ticketing.service.AccountService;
import com.ticketing.service.EventOrganizerService;

public class EventOrganizerServicesPage {
    AccountService accountService;
    EventOrganizerService eventOrganizerService;
    DatabaseRepository databaseRepository;

    EventOrganizerServicesPage(DatabaseRepository databaseRepository, AccountService accountService) {
        this.databaseRepository = databaseRepository;
        this.accountService = accountService;
        this.eventOrganizerService = EventOrganizerService.getInstance(databaseRepository);
    }
}
