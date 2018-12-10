package exige.supply.vortex;

import exige.supply.singularityengine.SingularityEngine;
import exige.supply.singularityengine.entities.Player;
import exige.supply.singularityengine.entities.PlayerCharacter;
import exige.supply.singularityengine.modules.ResourceLoader;
import exige.supply.vortex.entities.players.VortexPlayer;
import exige.supply.vortex.entities.players.VortexPlayerTwo;
import exige.supply.vortex.levels.L_PeachyRuins;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The VortexGame Class.
 * Handles the Vortex game execution and management.
 *
 * @author Ali Shariatmadari
 */

public class VortexGame {

    public static SingularityEngine vortex; // Declare instance of Game Engine

    public static void main(String[] args) {
        showSplash(); // Show game splash screen
    }

    public static void startGame() { // Starts the game
        vortex = new SingularityEngine("Vortex", true); // Instantiate Game engine for Vortex
        vortex.setLevel(new L_PeachyRuins()); // Set level to peachy ruins

        Player[] players = new Player[2]; // Create 2 player array
        players[0] = new VortexPlayer(PlayerCharacter.JACK, vortex.getLevel()); // Add player 1 to the array
        players[1] = new VortexPlayerTwo(PlayerCharacter.JORDAN, vortex.getLevel()); // Add player 2 to the array
        vortex.setPlayers(players); // Add player array to game

        vortex.start(); // start game
    }

    public static void promptReplay(boolean playerOneWin){ // Prompt replay window
        JWindow window = new JWindow(); // Create window
        window.setLayout(new BorderLayout()); // Set layout to border layout

        if (playerOneWin){
            ImageIcon logo = ResourceLoader.getImageIcon("prompts/Player1Win.png"); // Load image file
            window.getContentPane().add(new JLabel(logo), BorderLayout.PAGE_START); // Add logo to the top of window
        } else {
            ImageIcon logo = ResourceLoader.getImageIcon("prompts/Player2Win.png"); // Load image file
            window.getContentPane().add(new JLabel(logo), BorderLayout.PAGE_START); // Add logo to the top of window
        }

        /*EXIT BUTTON*/
        Button exitButton = new Button();
        exitButton.setLabel("EXIT"); // Set button text
        exitButton.setBackground(Color.BLACK); // Set Button BG color to black
        exitButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(exitButton, BorderLayout.LINE_START); // Add button to left side
        exitButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /*REPLAY BUTTON*/
        Button startButton = new Button();
        startButton.setLabel("REPLAY"); // Set button text
        startButton.setBackground(Color.BLACK); // Set Button BG color to black
        startButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(startButton, BorderLayout.CENTER);  // Add button to center side
        startButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // Restart game
                // Close window
                window.setVisible(false);
                window.dispose();
            }
        });

        window.pack(); // Pack contents
        window.setLocationRelativeTo(null); // Center window
        window.setVisible(true); // Make window visible
    }

    // Show Game splash screen
    private static void showSplash() {
        JWindow window = new JWindow(); // Create window
        window.setLayout(new BorderLayout()); // Set layout to border layout

        ImageIcon logo = ResourceLoader.getImageIcon("VortexLogo.png"); // Load logo file
        window.getContentPane().add(new JLabel(logo), BorderLayout.PAGE_START); // Add logo to the top of window
        ImageIcon engineLogo = ResourceLoader.getImageIcon("SingularityEngineBadge.png"); // Load logo file
        window.getContentPane().add(new JLabel(engineLogo), BorderLayout.PAGE_END); // Add logo to the top of window

        /*EXIT BUTTON*/
        Button exitButton = new Button();
        exitButton.setLabel("EXIT"); // Set button text
        exitButton.setBackground(Color.BLACK); // Set Button BG color to black
        exitButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(exitButton, BorderLayout.LINE_START); // Add button to left side
        exitButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /*START BUTTON*/
        Button startButton = new Button();
        startButton.setLabel("START"); // Set button text
        startButton.setBackground(Color.BLACK); // Set Button BG color to black
        startButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(startButton, BorderLayout.CENTER);  // Add button to center side
        startButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // If the start button is pressed, start game
                // Close window
                window.setVisible(false);
                window.dispose();
            }
        });

        /*HELP BUTTON*/
        Button helpButton = new Button();
        helpButton.setLabel("HELP"); // Set button text
        helpButton.setBackground(Color.BLACK); // Set Button BG color to black
        helpButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(helpButton, BorderLayout.LINE_END);  // Add button to right side
        helpButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                showHelp(false); // Show help window
                // Close splash window
                window.setVisible(false);
                window.dispose();
            }
        });

        window.pack(); // Pack contents
        window.setLocationRelativeTo(null); // Center window
        window.setVisible(true); // Make window visible
    }

    // Show Game splash screen
    public static void showPauseMenu() {
        JWindow window = new JWindow(); // Create window
        window.setLayout(new BorderLayout()); // Set layout to border layout

        ImageIcon logo = ResourceLoader.getImageIcon("VortexLogo.png"); // Load logo file
        window.getContentPane().add(new JLabel(logo), BorderLayout.PAGE_START); // Add logo to the top of window

        /*EXIT BUTTON*/
        Button exitButton = new Button();
        exitButton.setLabel("EXIT"); // Set button text
        exitButton.setBackground(Color.BLACK); // Set Button BG color to black
        exitButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(exitButton, BorderLayout.LINE_START); // Add button to left side
        exitButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /*RESUME BUTTON*/
        Button startButton = new Button();
        startButton.setLabel("RESUME"); // Set button text
        startButton.setBackground(Color.BLACK); // Set Button BG color to black
        startButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(startButton, BorderLayout.CENTER);  // Add button to center side
        startButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close window
                window.setVisible(false);
                window.dispose();
            }
        });

        /*HELP BUTTON*/
        Button helpButton = new Button();
        helpButton.setLabel("HELP"); // Set button text
        helpButton.setBackground(Color.BLACK); // Set Button BG color to black
        helpButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(helpButton, BorderLayout.LINE_END);  // Add button to right side
        helpButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                showHelp(true); // Show help window
                // Close splash window
                window.setVisible(false);
                window.dispose();
            }
        });

        window.pack(); // Pack contents
        window.setLocationRelativeTo(null); // Center window
        window.setVisible(true); // Make window visible
    }

    // Show game help menu
    private static void showHelp(boolean isPause){
        JWindow window = new JWindow(); // Create window
        window.setLayout(new BorderLayout()); // Set layout to border layout

        ImageIcon logo = ResourceLoader.getImageIcon("help/HowToPlay.png"); // Load image file
        window.getContentPane().add(new JLabel(logo), BorderLayout.PAGE_START); // Add logo to the top of window

        /*BACK BUTTON*/
        Button exitButton = new Button();
        exitButton.setLabel("BACK"); // Set button text
        exitButton.setBackground(Color.BLACK); // Set Button BG color to black
        exitButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(exitButton, BorderLayout.LINE_START); // Add button to left side
        exitButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPause) { // if the game is not paused
                    showSplash(); // Show the game splash menu
                } else {
                    showPauseMenu(); // Show the pause menu
                }
                // Close splash window
                window.setVisible(false);
                window.dispose();
            }
        });

        /*CONTROLS BUTTON*/
        Button startButton = new Button();
        startButton.setLabel("CONTROLS"); // Set button text
        startButton.setBackground(Color.BLACK); // Set Button BG color to black
        startButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(startButton, BorderLayout.CENTER);  // Add button to center side
        startButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                showControls(isPause); // Show the game controls window
                // Close window
                window.setVisible(false);
                window.dispose();
            }
        });

        window.pack(); // Pack contents
        window.setLocationRelativeTo(null); // Center window
        window.setVisible(true); // Make window visible
    }

    // Show game controls menu
    private static void showControls(boolean isPause){
        JWindow window = new JWindow(); // Create window
        window.setLayout(new BorderLayout()); // Set layout to border layout

        ImageIcon logo = ResourceLoader.getImageIcon("help/Controls.png"); // Load image file
        window.getContentPane().add(new JLabel(logo), BorderLayout.PAGE_START); // Add logo to the top of window

        /*BACK BUTTON*/
        Button exitButton = new Button();
        exitButton.setLabel("BACK"); // Set button text
        exitButton.setBackground(Color.BLACK); // Set Button BG color to black
        exitButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(exitButton, BorderLayout.LINE_START); // Add button to left side
        exitButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                showSplash(); // Show the game splash menu
                // Close splash window
                window.setVisible(false);
                window.dispose();
            }
        });

        /*HELP BUTTON*/
        Button startButton = new Button();
        startButton.setLabel("HELP"); // Set button text
        startButton.setBackground(Color.BLACK); // Set Button BG color to black
        startButton.setForeground(Color.WHITE); // Set button BG color to white
        window.getContentPane().add(startButton, BorderLayout.CENTER);  // Add button to center side
        startButton.addActionListener(new ActionListener() { // Add action handler for when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                showHelp(isPause); // Show the help window
                // Close window
                window.setVisible(false);
                window.dispose();
            }
        });

        window.pack(); // Pack contents
        window.setLocationRelativeTo(null); // Center window
        window.setVisible(true); // Make window visible
    }
}