package edu.Projects.TicketingSystem.Model;

import edu.Projects.TicketingSystem.Model.EventComponents.CustomerTicket;
import edu.Projects.TicketingSystem.Model.EventComponents.Event;
import edu.Projects.TicketingSystem.Model.EventComponents.Ticket;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    EventOrganizer CreateEventOrganizer() {
        return new EventOrganizer(1, "mostafa", "1234", "mostafa@gmail.com", "01093826360", 120.0);
    }

    EventOrganizer CreateEventOrganizerWithEvents(int numEvents) {
        EventOrganizer eventOrganizer = CreateEventOrganizer();
        for (int i = 1; i <= numEvents; i++) {
            eventOrganizer.createEvent(i, "Event " + i, "event type", "event description");
        }
        return eventOrganizer;
    }

    EventOrganizer CreateEventOrganizerWithTicketAdded() {
        EventOrganizer eventOrganizer = CreateEventOrganizerWithEvents(1);
        Ticket ticket = new Ticket(1, 1, 100, "ticket", "", LocalDate.now(), false, 10, 10.0);
        eventOrganizer.addTicketForEvent(1, ticket);
        return eventOrganizer;
    }

    Customer CreateCustomer() {
        return new Customer(30, "mostafa", "1234", "mostafa@gmail.com", "01093826360", 120.0);
    }

    @Test
    void buyTicket() {
        EventOrganizer eventOrganizer = CreateEventOrganizerWithTicketAdded();
        Customer customer = CreateCustomer();
        CustomerTicket customerTicket = eventOrganizer.getEvents().get(0).getTicketByTicketId(1).createCustomerTicket(customer);
        customer.buyTicket(customerTicket);

        CustomerTicket customerTicketRes = customer.getTickets().get(0);

        assertEquals(customer.getTickets().get(0), customerTicketRes);
    }

    @Test
    void refundTicket() {
    }
}