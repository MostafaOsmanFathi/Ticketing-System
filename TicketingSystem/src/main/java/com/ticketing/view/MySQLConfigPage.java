package com.ticketing.view;

import com.ticketing.repository.DatabaseRepository;
import com.ticketing.repository.MySqlRepository;

import javax.swing.*;
import java.awt.*;

public class MySQLConfigPage extends BaseWindow {
    public MySQLConfigPage() {
        super("MySQL Configuration");

        // Get the main panel from BaseWindow
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("MySQL Database Configuration", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JTextField hostField = new JTextField("localhost");
        JTextField portField = new JTextField("3306");
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JTextField dbNameField = new JTextField();

        formPanel.add(new JLabel("Host:"));
        formPanel.add(hostField);
        formPanel.add(new JLabel("Port:"));
        formPanel.add(portField);
        formPanel.add(new JLabel("Username:"));
        formPanel.add(userField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passField);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Connect");
        JButton backButton = new JButton("back");
        JButton defaultButton = new JButton("Default Database");

        buttonPanel.add(defaultButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        // Add components
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        char[] pass = passField.getPassword();
        String passString = new String(pass);
        // Button Action
        saveButton.addActionListener(e -> {
            dispose();
            DatabaseRepository databaseRepository = MySqlRepository.getInstance(hostField.getText(), Integer.parseInt(portField.getText()), userField.getText(), passString);
            new MainMenu(databaseRepository);
        });

        //back button
        backButton.addActionListener(e -> {
            dispose();
            new DatabaseSelectionPage();
        });

        defaultButton.addActionListener(e -> {
            DatabaseRepository databaseRepository = MySqlRepository.getInstance();
            dispose();
            new MainMenu(databaseRepository);

        });

        revalidate();
        repaint();
    }
}
