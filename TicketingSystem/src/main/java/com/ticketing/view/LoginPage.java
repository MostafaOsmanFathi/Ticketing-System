package com.ticketing.view;

import com.ticketing.enums.AccountType;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.repository.MySqlRepository;
import com.ticketing.service.AccountService;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends BaseWindow {

    public LoginPage(DatabaseRepository databaseRepository) {
        super("Login - Ticketing System");

        // Get main panel from BaseWindow
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Spacing

        // Email Label & Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        JTextField email = new JTextField(15);
        formPanel.add(email, gbc);

        // Password Label & Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        // Login Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        formPanel.add(loginButton, gbc);

        // Back Button
        gbc.gridy = 3; // Move to next row
        JButton back = new JButton("Back");
        formPanel.add(back, gbc);

        // Add Form Panel to Main Panel
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Back Button Functionality
        back.addActionListener(e -> {
            dispose();
            new MainMenu(databaseRepository);
        });

        loginButton.addActionListener(e -> {
            AccountService accountService = new AccountService(databaseRepository);
            char[] passwordChars = passwordField.getPassword(); // Get password as char[]
            String password = new String(passwordChars);

            if (accountService.login(email.getText(), password)) {
                JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                AccountType accountType = databaseRepository.getAccountType(accountService.getAccount().getAccountId());
                if (accountType.equals(AccountType.Customer)) {
                    new CustomerServicesPage(databaseRepository, accountService);
                } else if (accountType.equals(AccountType.EventOrganizer)) {
                    new EventOrganizerServicesPage(databaseRepository, accountService);
                } else {
                    accountService.logout();
                    JOptionPane.showMessageDialog(null, "Login failed!", "Failed", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Login failed!", "Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Refresh UI
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenu(MySqlRepository.getInstance());
        });
    }
}