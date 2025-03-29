package com.ticketing.repository;

import com.ticketing.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseRepositoryTest {
    private DatabaseRepository repository;

    // Factory method to provide instances of both repositories
    static Stream<DatabaseRepository> repositoryProvider() {
        return Stream.of(
                MySqlRepository.getInstance(),
                SqlLiteDatabaseRepository.getInstance()
        );
    }

    @BeforeEach
    void setup() {
        // Default to MySQL for now; will be overridden by parameterized tests
        repository = MySqlRepository.getInstance();
        repository.resetDatabase();
    }

    @ParameterizedTest
    @MethodSource("repositoryProvider")
    void createAccount(DatabaseRepository repository) {
        repository.resetDatabase();
        Account account = new Account(1001, "User1", "user1@example.com", "pass1");
        boolean result = repository.createAccount(account, "Customer");
        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("repositoryProvider")
    void getAccount(DatabaseRepository repository) {
        repository.resetDatabase();
        Account account = new Account(1002, "User2", "user2@example.com", "pass2");
        repository.createAccount(account, "Customer");
        Account result = repository.getAccount("user2@example.com", "pass2");
        assertNotNull(result);
        assertEquals(1002, result.getAccountId());
    }

    @ParameterizedTest
    @MethodSource("repositoryProvider")
    void depositToAccountByAccountId(DatabaseRepository repository) {
        repository.resetDatabase();
        Account account = new Account(1003, "User3", "user3@example.com", "pass3");
        repository.createAccount(account, "Customer");
        boolean depositResult = repository.depositToAccountByAccountId(1003, 50.0);
        assertTrue(depositResult);
        Account updated = repository.getAccount("user3@example.com", "pass3");
        assertEquals(50.0, updated.getWalletBalance());
    }

    @ParameterizedTest
    @MethodSource("repositoryProvider")
    void withdrawAccountById(DatabaseRepository repository) {
        repository.resetDatabase();
        Account account = new Account(1004, "User4", "user4@example.com", "pass4");
        repository.createAccount(account, "Customer");
        repository.depositToAccountByAccountId(1004, 100.0);
        boolean withdrawResult = repository.withdrawAccountById(1004, 40.0);
        assertTrue(withdrawResult);
        Account updated = repository.getAccount("user4@example.com", "pass4");
        assertEquals(60.0, updated.getWalletBalance());
    }

    @ParameterizedTest
    @MethodSource("repositoryProvider")
    void resetDatabase(DatabaseRepository repository) {
        repository.resetDatabase();
    }

    @Test
    void readSeed() {
        repository.readSeed();
    }
}
