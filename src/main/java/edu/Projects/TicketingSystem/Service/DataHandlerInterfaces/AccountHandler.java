package edu.Projects.TicketingSystem.Service.DataHandlerInterfaces;

import edu.Projects.TicketingSystem.Model.Account;
import edu.Projects.TicketingSystem.Model.Customer;
import edu.Projects.TicketingSystem.Model.EventComponents.Event;

import java.util.Objects;


interface AccountHandler {

    Account loadAccountByEmail(String email);

    Account loadAccountById(int id);

    boolean updateBalance(Account account);

    boolean updateAllAccountData(Account account);

    boolean createAccount(Account account);

    default boolean deposit(Account account, double amount) {
        Objects.requireNonNull(account);
        if (amount <= 0) {
            return false;
        }
        account.setBalance(account.getBalance() + amount);
        if (!updateBalance(account)) {
            account.setBalance(account.getBalance() - amount);
            return true;
        } else return false;
    }

    default boolean withdraw(Account account, double amount) {
        Objects.requireNonNull(account);
        if (amount <= 0 || account.getBalance() < amount) {
            return false;
        }
        account.setBalance(account.getBalance() - amount);

        if (!updateBalance(account)) {
            account.setBalance(account.getBalance() + amount);
            return false;
        } else return true;
    }

    boolean addCustomer(Customer customer);

    boolean checkCustomer(String email);

    boolean addEventOrganizer(Customer customer);

    boolean checkEventOrganizer(String email);
    boolean regiseterCustomer(String name, String password, String email, String phoneNumber, Double balance);
    boolean regiseterEventOrganizer(String name, String password, String email, String phoneNumber, Double balance);
}
