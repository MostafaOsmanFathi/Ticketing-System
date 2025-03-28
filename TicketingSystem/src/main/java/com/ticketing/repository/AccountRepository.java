package com.ticketing.repository;

import com.ticketing.model.Account;

public interface AccountRepository {
    boolean createAccount(Account account, String accountType);

    Account getAccount(String email, String password);


    boolean depositToAccountByAccountId(int accountId, double amount);

    default boolean deposit(Account account, double amount) {
        return depositToAccountByAccountId(account.getAccountId(), amount);
    }

    public boolean withdrawAccountById(int accountId, double amount);


    default boolean withdraw(Account account, double amount) {
        return withdrawAccountById(account.getAccountId(), amount);
    }

    default boolean createEventOrganizerAccount(Account account) {
        return createAccount(account, "EventOrganizer");
    }

    default boolean createCustomerAccount(Account account) {
        return createAccount(account, "Customer");
    }
}
