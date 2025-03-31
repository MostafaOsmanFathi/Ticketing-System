package com.ticketing.model;

public class Account {
    private int accountId;
    private String userName;
    private String email;
    private String password;
    private double walletBalance;

    public Account(int accountId, String name, String email, String password) {
        this.accountId = accountId;
        this.userName = name;
        this.email = email;
        this.password = password;
        this.walletBalance = 0;

    }

    public Account(Account account) {
        this.accountId = account.getAccountId();
        this.userName = account.getUserName();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.walletBalance = account.getWalletBalance();
    }

    public boolean changePassword(String newPassword) {
        if (this.password.equals(newPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalStateException("amount must be greater than 0");
        }

        walletBalance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than 0");
        }
        walletBalance -= amount;
        return true;
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

    public int getAccountId() {
        return accountId;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public String getPassword() {
        return password;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
