package com.ticketing.view;

import com.ticketing.repository.DatabaseRepository;
import com.ticketing.service.AccountService;
import com.ticketing.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class AccountServicesPage extends BaseWindow {
    AccountService accountService;
    DatabaseRepository databaseRepository;
    private JLabel emailLabel;
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private JTextField amountField;

    public AccountServicesPage(DatabaseRepository databaseRepository, AccountService accountService, Object cameFrom) {
        super("Account Balance - Ticketing System");
        this.databaseRepository = databaseRepository;
        this.accountService = accountService;

        // Get main panel from BaseWindow
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Email Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailLabel = new JLabel();
        formPanel.add(emailLabel, gbc);

        // Name Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameLabel = new JLabel();
        formPanel.add(nameLabel, gbc);

        // Balance Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Balance:"), gbc);
        gbc.gridx = 1;
        balanceLabel = new JLabel();
        formPanel.add(balanceLabel, gbc);

        // Amount Input Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        amountField = new JTextField(15);
        formPanel.add(amountField, gbc);

        // Deposit Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton depositButton = new JButton("Deposit");
        formPanel.add(depositButton, gbc);

        // Withdraw Button
        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton withdrawButton = new JButton("Withdraw");
        formPanel.add(withdrawButton, gbc);

        // Back Button
        gbc.gridx = 0;
        gbc.gridy = 6;
        JButton backButton = new JButton("Back");
        formPanel.add(backButton, gbc);

        // Add Form Panel to Main Panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        setEmail(accountService.getAccount().getEmail());
        setUserName(accountService.getAccount().getUserName());
        setBalance("" + accountService.getAccount().getWalletBalance());
        // Back Button Action
        backButton.addActionListener(e -> {
            if (cameFrom instanceof CustomerService) {
                new CustomerServicesPage(databaseRepository, accountService);
            } else {
                new EventOrganizerServicesPage(databaseRepository, accountService);
            }
            dispose();
        });
        depositButton.addActionListener(
                e -> {
                    int amount = Integer.parseInt(amountField.getText());
                    accountService.deposit(amount);
                    setBalance("" + accountService.getAccount().getWalletBalance());
                    revalidate();
                    repaint();
                }
        );
        withdrawButton.addActionListener(
                e -> {
                    int amount = Integer.parseInt(amountField.getText());
                    accountService.withdraw(amount);
                    setBalance("" + accountService.getAccount().getWalletBalance());
                    revalidate();
                    repaint();
                }
        );

        // Refresh UI
        revalidate();
        repaint();
    }

    public void setEmail(String email) {
        emailLabel.setText(email);
    }

    public void setUserName(String name) {
        nameLabel.setText(name);
    }

    public void setBalance(String balance) {
        balanceLabel.setText(balance);
    }
}
