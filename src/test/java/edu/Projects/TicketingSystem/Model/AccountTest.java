package edu.Projects.TicketingSystem.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account createAccount() {
        return new Account(1, "mostafa", "1234", "mostafa@gmail.com", "01093826360", 120.0);
    }

    @Test
    void getName() {
        Account account = createAccount();
        assertEquals("mostafa", account.getName());
    }

    @Test
    void setName() {
        Account account = createAccount();
        account.setName("mostafa2");
        assertEquals("mostafa2", account.getName());
    }

    @Test
    void getPassword() {
        Account account = createAccount();
        assertEquals("1234", account.getPassword());
    }

    @Test
    void setPassword() {
        Account account = createAccount();
        account.setPassword("1234mostafa");
        assertEquals("1234mostafa", account.getPassword());
    }

    @Test
    void getEmail() {
        Account account = createAccount();
        assertEquals("mostafa@gmail.com", account.getEmail());
    }

    @Test
    void setEmail() {
        Account account = createAccount();
        account.setEmail("mostafa2@gmail.com");
        assertEquals("mostafa2@gmail.com", account.getEmail());
    }

    @Test
    void getPhone() {
        Account account = createAccount();
        assertEquals("01093826360", account.getPhone());
    }

    @Test
    void setPhone() {
        Account account = createAccount();
        account.setPhone("01093826360");
    }

    @Test
    void getBalance() {
        Account account = createAccount();
        assertEquals(120.0, account.getBalance());
    }

    @Test
    void setBalance() {
        Account account = createAccount();
        account.setBalance(600.0);
        assertEquals(600.0, account.getBalance());
    }

    @Test
    void getId() {
        Account account = createAccount();
        assertEquals(1, account.getId());
    }

    @Test
    void withdraw() {
        Account account = createAccount();
        account.setBalance(600.0);
        account.withdraw(100.0);
        assertEquals(500.0, account.getBalance());
        assertFalse(account.withdraw(10000.0));
    }

    @Test
    void deposit() {
        Account account = createAccount();
        account.setBalance(600.0);
        account.deposit(100.0);
        assertEquals(700.0, account.getBalance());
        assertFalse(account.deposit(-10000.0));
    }
}