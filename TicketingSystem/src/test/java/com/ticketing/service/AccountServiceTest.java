package com.ticketing.service;

import com.ticketing.enums.AccountType;
import com.ticketing.repository.AccountRepository;
import com.ticketing.repository.DatabaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    private AccountService accountService;
    private DatabaseRepository databaseRepository;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = DatabaseRepository.getInstance();
        databaseRepository = DatabaseRepository.getInstance();
        accountService = new AccountService();
        databaseRepository.clearDatabase();
        accountService.logout();
    }

    @Test
    void registerAccount() {
        assertTrue(accountService.register(AccountType.Customer, 1, "test", "test@example.com", "password123"));
        assertTrue(accountService.login("test@example.com", "password123"));
        accountService.logout();
    }

    @Test
    void login_withValidCredentials_shouldSucceed() {
        // Assuming there is an account with these credentials in the database
        assertTrue(accountService.register(AccountType.Customer, 1, "test", "test@example.com", "password123"));
        assertTrue(accountService.login("test@example.com", "password123"));
    }

    @Test
    void login_withInvalidCredentials_shouldThrowException() {
        assertFalse(accountService.login("wrong@example.com", "wrongpassword"));
    }

    @Test
    void deposit_whenLoggedIn_shouldSucceed() {
        assertTrue(accountService.register(AccountType.Customer, 1, "test", "test@example.com", "password123"));
        accountService.login("test@example.com", "password123");
        assertTrue(accountService.deposit(100.0));
    }

    @Test
    void deposit_whenNotLoggedIn_shouldThrowException() {
        Exception exception = assertThrows(IllegalCallerException.class, () -> {
            accountService.deposit(100.0);
        });

        assertEquals("you are not logged in", exception.getMessage());
    }

    @Test
    void withdraw_whenLoggedIn_shouldSucceed() {
        assertTrue(accountService.register(AccountType.Customer, 1, "test", "test@example.com", "password123"));
        accountService.login("test@example.com", "password123");
        accountService.deposit(50.0);
        assertTrue(accountService.withdraw(50.0));
    }

    @Test
    void withdraw_whenNotLoggedIn_shouldThrowException() {
        Exception exception = assertThrows(IllegalCallerException.class, () -> {
            accountService.withdraw(50.0);
        });

        assertEquals("you are not logged in", exception.getMessage());
    }
}
