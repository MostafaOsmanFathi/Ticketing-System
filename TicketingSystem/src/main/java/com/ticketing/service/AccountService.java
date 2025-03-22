package com.ticketing.service;

import com.ticketing.model.Account;
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
        this.payments = new ArrayList<PaymentInterface>();
        this.payments.add(new sudoPayment());
        if (account == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return true;
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
                && accountRepository.deposit(account, amount)
                && account.deposit(amount);
    }

    public void logout() {
        this.account = null;
        this.payments = null;
        defaultPaymentIdx = 0;
    }
}
