package com.ticketing.view;


import javax.swing.*;
import java.awt.*;

public class DatabaseSelectionPage extends BaseWindow {
    public DatabaseSelectionPage() {
        super("Select Database");

        // Get the main panel from BaseWindow
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Choose Your Database", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 10)); // 1 row, 2 columns
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // SQLite Button
        JButton sqliteButton = new JButton("SQLite Database");
        sqliteButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // MySQL Button
        JButton mysqlButton = new JButton("MySQL Database");
        mysqlButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add buttons to panel
        buttonPanel.add(sqliteButton);
        buttonPanel.add(mysqlButton);

        // Add components to main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Button Actions
        sqliteButton.addActionListener(e -> {
            dispose();
            new SQLiteConfigPage();
        });
        mysqlButton.addActionListener(e -> {
            dispose();
            new MySQLConfigPage();
        });

        // Refresh UI
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DatabaseSelectionPage::new);
    }
}
