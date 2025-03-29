package com.ticketing.view;

import com.ticketing.repository.DatabaseRepository;
import com.ticketing.repository.SqlLiteDatabaseRepository;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SQLiteConfigPage extends BaseWindow {
    public SQLiteConfigPage() {
        super("SQLite Configuration");

        // Get the main panel from BaseWindow
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("SQLite Database Selection", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // File Selection Panel
        JPanel filePanel = new JPanel();
        JTextField filePathField = new JTextField(20);
        JButton browseButton = new JButton("Browse");

        filePanel.add(filePathField);
        filePanel.add(browseButton);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton connectButton = new JButton("Connect");
        buttonPanel.add(connectButton);

        // Add components
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(filePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Browse Button Action
        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filePathField.setText(selectedFile.getAbsolutePath());
            }
        });

        // Connect Button Action
        connectButton.addActionListener(e -> {
            String connectionInfo = filePathField.getText();
            DatabaseRepository databaseRepository = SqlLiteDatabaseRepository.getInstance(connectionInfo);
            dispose();
            new MainMenu(databaseRepository);
        });

        revalidate();
        repaint();
    }
}
