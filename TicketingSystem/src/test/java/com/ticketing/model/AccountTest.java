package com.ticketing.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account(1, "JohnDoe", "john@example.com", "password123");
    }

    @Test
    void changePassword() {
        assertTrue(account.changePassword("password123"));
        assertEquals("password123", account.getPassword());

        assertFalse(account.changePassword("newPassword"));
        assertEquals("password123", account.getPassword());
    }

    @Test
    void deposit() {
        assertTrue(account.deposit(100.0));
        assertEquals(100.0, account.getWalletBalance(), 0.001);

        account.deposit(50.0);
        assertEquals(150.0, account.getWalletBalance(), 0.001);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> account.deposit(-10));
        assertEquals("amount must be greater than 0", exception.getMessage());
    }

    @Test
    void withdraw() {
        account.deposit(200.0);

        assertTrue(account.withdraw(50.0));
        assertEquals(150.0, account.getWalletBalance(), 0.001);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertEquals("amount must be greater than 0", exception.getMessage());
    }

    @Test
    void getUserName() {
        assertEquals("JohnDoe", account.getUserName());
    }

    @Test
    void setUserName() {
        account.setUserName("JaneDoe");
        assertEquals("JaneDoe", account.getUserName());
    }

    @Test
    void getEmail() {
        assertEquals("john@example.com", account.getEmail());
    }

    @Test
    void setEmail() {
        account.setEmail("jane@example.com");
        assertEquals("jane@example.com", account.getEmail());
    }

    @Test
    void getAccountId() {
        assertEquals(1, account.getAccountId());
    }

    @Test
    void getWalletBalance() {
        assertEquals(0.0, account.getWalletBalance(), 0.001);
        account.deposit(100.0);
        assertEquals(100.0, account.getWalletBalance(), 0.001);
    }

    @Test
    void getPassword() {
        assertEquals("password123", account.getPassword());
    }
}
