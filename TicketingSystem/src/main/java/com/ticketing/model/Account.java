package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String userName;
    private String email;
    private String password;
    private double walletBalance;

    private int defaultPaymentIdx = 0;
    private final List<PaymentInterface> payments;

    public Account(String name, String email, String password) {
        this.userName = name;
        this.email = email;
        this.password = password;
        this.walletBalance = 0;
        this.payments = new ArrayList<PaymentInterface>();
        this.payments.add(new sudoPayment());
    }

    public boolean deposit(int amount, int paymentIdx) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than 0");
        } else if (payments.isEmpty()) {
            throw new IllegalStateException("No payment available");
        } else if (paymentIdx >= payments.size() || paymentIdx < 0) {
            throw new IllegalStateException("Invalid payment index");
        }

        if (!payments.get(paymentIdx).collect(amount)) {
            return false;
        }

        walletBalance += amount;
        return true;
    }

    public boolean withdraw(int amount, int paymentIdx) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than 0");
        } else if (payments.isEmpty()) {
            throw new IllegalStateException("No payment available");
        } else if (paymentIdx >= payments.size() || paymentIdx < 0) {
            throw new IllegalStateException("Invalid payment index");
        }

        if (!payments.get(paymentIdx).pay(amount)) {
            return false;
        }

        walletBalance -= amount;
        return true;
    }

    public boolean changePassword(String newPassword) {
        if (this.password.equals(newPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public boolean deposit(int amount) {
        return deposit(amount, defaultPaymentIdx);
    }

    public boolean withdraw(int amount) {
        return withdraw(amount, defaultPaymentIdx);
    }

    public int getDefaultPaymentIdx() {
        return defaultPaymentIdx;
    }

    public void setDefaultPaymentIdx(int defaultPaymentIdx) {
        this.defaultPaymentIdx = defaultPaymentIdx;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
