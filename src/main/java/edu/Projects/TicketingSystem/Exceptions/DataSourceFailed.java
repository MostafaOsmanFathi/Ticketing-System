package edu.Projects.TicketingSystem.Exceptions;

public class DataSourceFailed extends RuntimeException {
    public DataSourceFailed(String message) {
        super(message);
    }
}
