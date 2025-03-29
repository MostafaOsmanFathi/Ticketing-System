package com.ticketing.view;


import javax.swing.*;
import java.awt.*;

public class MainMenu extends BaseWindow {

    public MainMenu() {
        super("Ticketing System");

        // Get the main panel from BaseWindow
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome to Ticketing System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10)); // 2 rows, 1 column, spacing
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add buttons to panel
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        // Add components to main panel
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Refresh UI
        revalidate();
        repaint();
        loginButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });
        registerButton.addActionListener(e -> {
            dispose();
            new RegisterPage();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
