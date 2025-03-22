package com.ticketing.repository;

import com.ticketing.model.Account;

public interface AccountRepository {
    boolean createAccount(Account account, String accountType);

    Account getAccount(String email, String password);

    boolean deposit(Account account, double amount);

    boolean withdraw(Account account, double amount);

    default boolean createEventOrganizerAccount(Account account) {
        return createAccount(account, "EventOrganizer");
    }

    default boolean createEventCustomerAccount(Account account) {
        return createAccount(account, "Customer");
    }
}
