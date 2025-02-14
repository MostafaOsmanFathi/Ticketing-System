package edu.Projects.TicketingSystem.Model;

import edu.Projects.TicketingSystem.Repository.Interfaces.AccountDataHandler;

public class Account {
    private final int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private Double balance;
    private Address address;

    AccountDataHandler accountDataHandler;

    private Account(AccountDataHandler accountDataHandler, int id, String name, String password, String email, String phone, Double balance, Address address) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
        this.address = address;
        this.accountDataHandler = accountDataHandler;

        accountDataHandler.createAccount(this);
    }

    private Account(Account account) {
        this.id = account.id;
        this.name = account.name;
        this.password = account.password;
        this.email = account.email;
        this.phone = account.phone;
        this.balance = account.balance;
        this.address = account.address;
        this.accountDataHandler = account.accountDataHandler;
    }

    public static Account createAccount(AccountDataHandler accountDataHandler, int id, String name, String password, String email, String phone, Double balance, Address address) {
        Account account = new Account(accountDataHandler, id, name, password, email, phone, balance, address);
        if (accountDataHandler.createAccount(account)) {
            return account;
        } else {
            return null;
        }
    }

    public static Account loadAccount(AccountDataHandler accountDataHandler, int id) {
        return accountDataHandler.loadAccount(id);
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
        accountDataHandler.updateBalance(this);
    }

    public int getId() {
        return id;
    }
}
