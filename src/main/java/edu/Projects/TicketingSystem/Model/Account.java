package edu.Projects.TicketingSystem.Model;

import edu.Projects.TicketingSystem.Exceptions.DataSourceFailed;
import edu.Projects.TicketingSystem.Model.DataHandlerInterfaces.AccountHandler;

import java.util.Objects;

public sealed class Account permits Customer, EventOrganizer {
    private final int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private Double balance;

    AccountHandler accountDataHandler;

    protected Account(AccountHandler accountDataHandler, int id, String name, String password, String email, String phone, Double balance) {
        Objects.requireNonNull(accountDataHandler);
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
        this.accountDataHandler = accountDataHandler;
        if (!this.accountDataHandler.createAccount(this)) {
            throw new DataSourceFailed("Account creation failed");
        }
    }

    protected Account(Account account) {
        this(account.accountDataHandler, account.id, account.name, account.password, account.email, account.phone, account.balance);
        Objects.requireNonNull(account);
    }

    protected Account(AccountHandler dataHandler, String email, String password) {
        this(dataHandler.loadAccountByEmailAndPassword(email, password));
        Objects.requireNonNull(dataHandler);
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
    }

//    protected static Account createAccount(AccountDataHandler accountDataHandler, int id, String name, String password, String email, String phone, Double balance) {
//        Account account = new Account(accountDataHandler, id, name, password, email, phone, balance);
//        if (accountDataHandler.createAccount(account)) {
//            return account;
//        } else {
//            return null;
//        }
//    }


    public static Account loadAccount(AccountHandler accountDataHandler, String email, String password) {
        return accountDataHandler.loadAccountByEmailAndPassword(email, password);
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
        accountDataHandler.updateBalance(this);
    }

    public int getId() {
        return id;
    }
}
