package com.ticketing.service;

import com.ticketing.enums.AccountType;
import com.ticketing.model.Account;
import com.ticketing.model.Customer;
import com.ticketing.model.EventOrganizer;
import com.ticketing.repository.AccountRepository;
import com.ticketing.repository.DatabaseRepository;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private Account account;

    private int defaultPaymentIdx;
    private List<PaymentInterface> payments;

    AccountRepository accountRepository;


    public AccountService() {
        this.account = null;
        this.accountRepository = DatabaseRepository.getInstance();
        this.payments = new ArrayList<PaymentInterface>();
        this.payments.add(new sudoPayment());
    }

    public boolean login(String email, String password) {
        this.account = accountRepository.getAccount(email, password);

        if (accountRepository.getAccountType(account.getAccountId()).equals(AccountType.Customer)) {
            int customerId = accountRepository.getCustomerId(account.getAccountId());
            account = new Customer(account, customerId);
        } else {
            int eventOrganizerId = accountRepository.getEventOrganizerId(account.getAccountId());
            account = new EventOrganizer(account, eventOrganizerId);
        }

        this.payments = new ArrayList<PaymentInterface>();
        this.payments.add(new sudoPayment());
        if (account == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return true;
    }

    public boolean register(AccountType accountType, int accountId, String name, String email, String password) {
        Account account = new Account(accountId, name, email, password);
        if (accountType == AccountType.Customer) {
            return accountRepository.createCustomerAccount(account);
        } else {
            return accountRepository.createEventOrganizerAccount(account);
        }
    }

    public boolean deposit(double amount) {
        if (this.account == null) {
            throw new IllegalCallerException("you are not logged in");
        }
        return payments.get(defaultPaymentIdx).collect(amount)
                && accountRepository.deposit(account, amount)
                && account.deposit(amount);
    }

    public boolean withdraw(double amount) {
        if (this.account == null) {
            throw new IllegalCallerException("you are not logged in");
        }

        return payments.get(defaultPaymentIdx).pay(amount)
                && accountRepository.withdraw(account, amount)
                && account.withdraw(amount);
    }

    public Account getAccount() {
        return account;
    }

    public void logout() {
        this.account = null;
        this.payments = null;
        defaultPaymentIdx = 0;
    }
}
