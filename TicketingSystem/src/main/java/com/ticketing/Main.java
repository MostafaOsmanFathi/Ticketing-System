package com.ticketing;

import com.ticketing.view.DatabaseSelectionPage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DatabaseSelectionPage::new);
    }
}