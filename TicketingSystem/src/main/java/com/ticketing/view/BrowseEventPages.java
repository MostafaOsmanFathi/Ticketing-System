package com.ticketing.view;

import com.ticketing.repository.DatabaseRepository;
import com.ticketing.service.AccountService;
import com.ticketing.service.EventOrganizerService;

public class BrowseEventPages {
    private AccountService accountService;
    private DatabaseRepository databaseRepository;
    private EventOrganizerService eventOrganizerService;

    BrowseEventPages(AccountService accountService, DatabaseRepository databaseRepository) {
        this.accountService = accountService;
        this.databaseRepository = databaseRepository;
        this.eventOrganizerService = EventOrganizerService.getInstance(databaseRepository);

    }
}
