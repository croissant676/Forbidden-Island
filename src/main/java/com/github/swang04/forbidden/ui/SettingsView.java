/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.ui;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.board.WaterMeter;
import dev.kason.forbidden.ImageStorage;
import dev.kason.forbidden.Log;
import dev.kason.forbidden.ui.BoardVisualizer;
import dev.kason.forbidden.ui.View;
import dev.kason.forbidden.ui.ViewManager;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;


public class SettingsView extends View {

    private static final SettingsView view = new SettingsView();
    private static JFrame currentWindow;

    public SettingsView() {
        super("Settings", "Settings", false);
    }

    public static JFrame getCurrentWindow() {
        return currentWindow;
    }

    public static void setCurrentWindow(JFrame currentWindow) {
        SettingsView.currentWindow = currentWindow;
    }

    public static SettingsView getInstance() {
        return view;
    }

    @Override
    public JComponent getDisplay() {
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BorderLayout());
        JButton startGameButton = new JButton("Start Game!");
        settingsPanel.add(startGameButton);

        JPanel seedPanel = new JPanel();
        JLabel setting0 = new JLabel("Seed:                              ");
        JSpinner spinner = new JSpinner();

        seedPanel.add(setting0);
        seedPanel.add(spinner);

        JPanel playerPanel = new JPanel();
        JLabel settingOne = new JLabel("Number of Players:        ");
        playerPanel.add(settingOne);
        String[] numPlayersPossibilities = {"2", "3", "4"};
        JComboBox<String> numPlayers = new JComboBox<>(numPlayersPossibilities);
        playerPanel.add(numPlayers);

        JPanel difficultyPanel = new JPanel();
        JLabel settingTwo = new JLabel("Difficulty:           ");
        String[] difficultiesPossible = {"Novice", "Normal", "Elite", "Legendary"};
        JComboBox<String> difficulty = new JComboBox<>(difficultiesPossible);
        difficultyPanel.add(settingTwo);
        difficultyPanel.add(difficulty);

        Font f = new Font("Trebuchet MS", Font.PLAIN, 18);
        setting0.setFont(f);
        settingOne.setFont(f);
        settingTwo.setFont(f);
        spinner.setFont(f);
        numPlayers.setFont(f);
        difficulty.setFont(f);
        startGameButton.setFont(f);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage bufferedImage = ViewManager.getScaledImage(ImageStorage.retrieveImage("regular_background.png"), 500, 300);
                g.drawImage(bufferedImage, 0, 0, null);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(50));
        panel.add(seedPanel);
        panel.add(playerPanel);
        panel.add(difficultyPanel);
        panel.add(Box.createVerticalStrut(50));

        settingsPanel.add(panel, BorderLayout.CENTER);
        settingsPanel.add(startGameButton, BorderLayout.SOUTH);

        Color transparent = ViewManager.getTransparent();
        seedPanel.setBackground(transparent);
        playerPanel.setBackground(transparent);
        difficultyPanel.setBackground(transparent);

        startGameButton.addActionListener((event) -> {
            Logger logger = Log.logger();
            logger.info("Displaying the board ui");
            ViewManager.getMainFrame().setVisible(false);
            String[] array = new String[Integer.parseInt((String) Objects.requireNonNull(numPlayers.getSelectedItem()))];
            currentWindow.setVisible(false);
            Arrays.fill(array, "sus");
            Game.setGame(new Game((Integer) spinner.getValue(), WaterMeter.getStateBasedOnName((String) Objects.requireNonNull(difficulty.getSelectedItem())), array));
            BoardVisualizer.createBoardUI();
        });
        settingsPanel.setSize(450, 280);
        return settingsPanel;
    }
}
