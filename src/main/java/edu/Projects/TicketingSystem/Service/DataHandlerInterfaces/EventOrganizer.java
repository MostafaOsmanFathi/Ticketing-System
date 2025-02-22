package edu.Projects.TicketingSystem.Service.DataHandlerInterfaces;

import edu.Projects.TicketingSystem.Service.CustomerService;

public class EventOrganizer {
    static EventOrganizer eventOrganizer = null;
    static DataHandler dataHandler = null;

    private EventOrganizer() {
    }

    public static EventOrganizer getCustomerService(DataHandler dataHandlerPram) {
        if (eventOrganizer == null) {
            eventOrganizer = new EventOrganizer();
            dataHandler = dataHandlerPram;
        }
        return eventOrganizer;
    }

}
