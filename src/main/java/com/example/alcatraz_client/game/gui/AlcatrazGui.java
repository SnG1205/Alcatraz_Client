package com.example.alcatraz_client.game.gui;

import com.example.alcatraz_client.PortFetcher;
import com.example.alcatraz_client.data.Client;
import com.example.alcatraz_client.rest.Caller;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class AlcatrazGui {

    public static void drawBoard(PortFetcher portFetcher) {
        Caller caller = new Caller();
        // Hauptfenster erstellen
        JFrame frame = new JFrame("Escape from Alcatraz");
        frame.setSize(600, 400); // Fenstergröße
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Titel
        JLabel titleLabel = new JLabel("Escape from Alcatraz");
        titleLabel.setFont(new Font("Stencil", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.lightGray);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.DARK_GRAY); // Titelhintergrund
        frame.add(titleLabel, BorderLayout.NORTH);

        // Hauptpanel für die Eingabefelder und Buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout()); // Flexibles Layout
        mainPanel.setBackground(Color.BLACK); // Hintergrundfarbe

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Abstände zwischen Komponenten
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Komponenten erstellen
        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.lightGray);

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        amountLabel.setForeground(Color.lightGray);

        JTextField usernameField = new JTextField(15); // Textfeld mit fester Breite
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField amountField = new JTextField(15); // Textfeld mit fester Breite
        amountField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton createLobbyButton = new JButton("Create Lobby");
        customizeButton(createLobbyButton);

        JButton joinLobbyButton = new JButton("Join Lobby");
        customizeButton(joinLobbyButton);

        JButton startGameButton = new JButton("Start Game");
        customizeButton(startGameButton);
        startGameButton.setVisible(false); // Anfangs nicht sichtbar

        JButton leaveLobbyButton = new JButton("Leave Lobby");
        customizeButton(leaveLobbyButton);
        leaveLobbyButton.setVisible(false);

        // Komponenten zum Panel hinzufügen
        gbc.gridx = 0;
        gbc.gridy = 0; // Erste Zeile
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0; // Erste Zeile, zweite Spalte
        mainPanel.add(usernameField, gbc);

        // Komponenten zum Panel hinzufügen
        gbc.gridx = 0;
        gbc.gridy = 1; // Erste Zeile
        mainPanel.add(amountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1; // Erste Zeile, zweite Spalte
        mainPanel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Zweite Zeile, beide Spalten
        mainPanel.add(createLobbyButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Dritte Zeile, beide Spalten
        mainPanel.add(joinLobbyButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Vierte Zeile, beide Spalten
        mainPanel.add(startGameButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Vierte Zeile, beide Spalten
        mainPanel.add(leaveLobbyButton, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);

        // Footer hinzufügen
        JLabel footerLabel = new JLabel("Get all players on the boat to win!");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setForeground(Color.LIGHT_GRAY);
        footerLabel.setOpaque(true);
        footerLabel.setBackground(Color.DARK_GRAY);
        frame.add(footerLabel, BorderLayout.SOUTH);

        // Fenster sichtbar machen
        frame.setVisible(true);

        // Actions hinzufügen
        createLobbyButton.addActionListener(e -> {
            String username = usernameField.getText();
            try {
                caller.createLobby(new Client(usernameField.getText(), Integer.parseInt(portFetcher.getPort())), 8080, Integer.parseInt(amountField.getText()));
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Lobby created by: " + username);
            startGameButton.setVisible(true);
            //createLobbyButton.setVisible(false);
            //joinLobbyButton.setVisible(false);
            //leaveLobbyButton.setVisible(true);
        });

        joinLobbyButton.addActionListener(e -> {
            String username = usernameField.getText();
            try {
                caller.joinLobby(new Client(usernameField.getText(), Integer.parseInt(portFetcher.getPort())), 8080);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Joined lobby as: " + username);
            //startGameButton.setVisible(true);
            //joinLobbyButton.setVisible(false);
            leaveLobbyButton.setVisible(true);
        });

        startGameButton.addActionListener(e -> {
            try {
                caller.startGame(new Client(usernameField.getText(), Integer.parseInt(portFetcher.getPort())), 8080);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Game started!");
        });

        leaveLobbyButton.addActionListener(e -> {
            try {
                caller.leaveLobby(new Client(usernameField.getText(), Integer.parseInt(portFetcher.getPort())), 8080);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
            joinLobbyButton.setVisible(true);
            leaveLobbyButton.setVisible(false);
            System.out.println("Left lobby");
        });
    }

    public static void main(String[] args) {

    }

    // Methode für konsistente Button-Stilierung
    private static void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.lightGray); // Hintergrundfarbe
        button.setForeground(Color.BLACK); // Schriftfarbe
        button.setFocusPainted(false); // Entfernt Fokus-Rand
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2)); // Hellgraue Umrandung
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand-Cursor

        // Hover-Effekt
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.LIGHT_GRAY); // Hintergrundfarbe bei Hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.lightGray); // Standardfarbe zurücksetzen
            }
        });
    }
}
