package com.ticketing.view;

import com.ticketing.enums.AccountType;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.repository.MySqlRepository;
import com.ticketing.service.AccountService;

import javax.swing.*;
import java.awt.*;

public class RegisterPage extends BaseWindow {

    public RegisterPage(DatabaseRepository databaseRepository) {
        super("Register - Ticketing System");

        // Get main panel from BaseWindow
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Spacing

        // Username Label & Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField(15);
        formPanel.add(usernameField, gbc);

        // Email Label & Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        JTextField emailField = new JTextField(15);
        formPanel.add(emailField, gbc);

        // Password Label & Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        // Account Type Selection
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Account Type:"), gbc);

        // Radio buttons
        JRadioButton customerRadio = new JRadioButton("Customer", true);
        JRadioButton organizerRadio = new JRadioButton("Event Organizer");

        // Grouping buttons
        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(customerRadio);
        accountTypeGroup.add(organizerRadio);

        gbc.gridx = 1;
        JPanel radioPanel = new JPanel();
        radioPanel.add(customerRadio);
        radioPanel.add(organizerRadio);
        formPanel.add(radioPanel, gbc);

        // Register Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton registerButton = new JButton("Register");
        formPanel.add(registerButton, gbc);

        // Back Button
        gbc.gridy = 5;
        JButton back = new JButton("Back");
        formPanel.add(back, gbc);

        // Add Form Panel to Main Panel
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Back Button Action
        back.addActionListener(e -> {
            dispose();
            new MainMenu(databaseRepository);
        });

        // Register Button Action
        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            AccountService accountService = new AccountService(databaseRepository);
            AccountType accountType;
            if (customerRadio.isSelected()) {
                accountType = AccountType.Customer;
            } else {
                accountType = AccountType.EventOrganizer;
            }
            if (accountService.register(accountType,1 ,username, email, password)) {
                JOptionPane.showMessageDialog(null, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new MainMenu(databaseRepository);
            } else {
                JOptionPane.showMessageDialog(null, "Registration failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Refresh UI
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DatabaseRepository databaseRepository = MySqlRepository.getInstance();
            new RegisterPage(databaseRepository);
        });
    }
}
