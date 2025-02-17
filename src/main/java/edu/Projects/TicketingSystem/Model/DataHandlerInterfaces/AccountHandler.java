package edu.Projects.TicketingSystem.Model.DataHandlerInterfaces;

import edu.Projects.TicketingSystem.Model.Account;

import java.util.Objects;


interface AccountHandler {

    Account loadAccountByEmailAndPassword(String email, String password);

    Account loadAccountById(int id);

    boolean updateBalance(Account account);

    boolean updateAllAccountData(Account account);

    boolean createAccount(Account account);

    default boolean deposit(Account account, int amount) {
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

    default boolean withdraw(Account account, int amount) {
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
}
