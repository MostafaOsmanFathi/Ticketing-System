package edu.Projects.TicketingSystem.Service;

import edu.Projects.TicketingSystem.Model.Account;
import edu.Projects.TicketingSystem.Model.Customer;
import edu.Projects.TicketingSystem.Model.EventOrganizer;
import edu.Projects.TicketingSystem.Service.DataHandlerInterfaces.DataHandler;

public class AccountService {
    static AccountService accountService = null;
    static DataHandler dataHandler = null;

    private AccountService() {

    }


    private Account GetAccount(String email) {
        return dataHandler.loadAccountByEmail(email);
    }


    Customer loginCustomerAccount(String email, String password) {
        Account account = GetAccount(email);
        if (account.getPassword().equals(password)) {
            if (dataHandler.checkCustomer(email)) {
                return new Customer(account);
            }
            return null;
        }
        return null;

    }

    EventOrganizer loginEventOrganizer(String email, String password) {
        Account account = GetAccount(email);
        if (account.getPassword().equals(password)) {
            if (dataHandler.checkCustomer(email)) {
                return new EventOrganizer(account);
            }
            return null;
        }
        return null;
    }

    boolean registerCustomerAccount(String name, String password, String email, String phoneNumber, Double balance) {
        Account account = GetAccount(email);
        if (account != null) {
            return false;
        }
        return dataHandler.regiseterCustomer(name, password, email, phoneNumber, balance);
    }

    boolean registerEventOrganizerAccount(String name, String password, String email, String phoneNumber, Double balance) {
        Account account = GetAccount(email);
        if (account != null) {
            return false;
        }
        return dataHandler.regiseterEventOrganizer(name, password, email, phoneNumber, balance);
    }


    public static AccountService getAccountService(DataHandler dataHandlerPram) {
        if (accountService == null) {
            accountService = new AccountService();
            dataHandler = dataHandlerPram;
        }
        return accountService;
    }

    public static boolean depositAccount(Account account, double amount) {
        return dataHandler.deposit(account, amount);
    }

    public static boolean withdrawAccount(Account account, double amount) {
        return dataHandler.deposit(account, amount);
    }
}
