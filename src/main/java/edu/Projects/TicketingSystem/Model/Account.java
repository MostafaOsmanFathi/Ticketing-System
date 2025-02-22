package edu.Projects.TicketingSystem.Model;

import edu.Projects.TicketingSystem.Exceptions.DataSourceFailed;

import java.util.Objects;

public sealed class Account permits Customer, EventOrganizer {
    private final int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private Double balance;


    protected Account(int id, String name, String password, String email, String phone, Double balance) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(password);
        Objects.requireNonNull(email);
        Objects.requireNonNull(phone);
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    protected Account(Account account) {
        this(account.id, account.name, account.password, account.email, account.phone, account.balance);
        Objects.requireNonNull(account);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }


    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        return true;
    }

}
