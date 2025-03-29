package com.ticketing.view;

import javax.swing.*;
import java.awt.*;

public class RegisterPage extends BaseWindow {

    public RegisterPage() {
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

        // Register Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton registerButton = new JButton("Register");
        formPanel.add(registerButton, gbc);

        //back button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton back = new JButton("Back");
        formPanel.add(back, gbc);

        // Add Form Panel to Main Panel
        mainPanel.add(formPanel, BorderLayout.CENTER);

        back.addActionListener(e -> {
            dispose();
            new MainMenu();
        });


        // Refresh UI
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterPage::new);
    }
}
