/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.ui;

import dev.kason.forbidden.Log;
import dev.kason.forbidden.ui.View;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.logging.Logger;


public class SettingsView extends View {

    private static final SettingsView view = new SettingsView();

    public SettingsView() {
        super("Settings", "intermediate", false);
    }

    public static SettingsView getInstance() {
        return view;
    }

    @Override
    public JComponent getDisplay() {
        JPanel settingsPanel = new JPanel();
        JButton startGameButton = new JButton("Start Game!");
        settingsPanel.add(startGameButton);
        JLabel settingOne = new JLabel("Number of Players:");
        settingsPanel.add(settingOne);
        JLabel settingTwo = new JLabel("Difficulty");
        settingsPanel.add(settingTwo);
        startGameButton.addActionListener((event) -> {
            Logger logger = Log.logger();
            logger.info("Displaying the board ui");
            GameView.getInstance().getDisplay();
        });

        return settingsPanel;
    }
}
