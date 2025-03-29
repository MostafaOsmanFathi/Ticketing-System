package com.ticketing.view;

import javax.swing.*;
import java.awt.*;

public class BaseWindow extends JFrame {

    public BaseWindow(String title) {
        // Set window title
        super(title);

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set a minimum size to prevent extreme shrinking
        setMinimumSize(new Dimension(400, 300));

        // Set a preferred size (adjustable)
        setPreferredSize(new Dimension(800, 600));

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Use a main panel to hold content
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // Add main panel to frame
        getContentPane().add(mainPanel);

        // Apply layout and make the frame visible
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BaseWindow("My App"));
    }
}
