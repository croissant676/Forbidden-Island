/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.ui;

import dev.kason.forbidden.ImageStorage;
import dev.kason.forbidden.Log;
import dev.kason.forbidden.ui.View;
import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class LossView extends View {

    private static final LossView view = new LossView();
    private static final Logger logger = Log.logger();

    private LossView() {
        super("You lost!", "Lost");
    }

    public static LossView getInstance() {
        return view;
    }

    @NotNull
    static JComponent getComponent(JPanel panel, JLabel winText) {
        winText.setFont(new Font("Trebuchet MS", Font.BOLD, 70));
        panel.add(winText);
        JButton button = new JButton("Exit Game");
        button.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        panel.add(button);
        button.addActionListener((e) -> System.exit(0));
        panel.setSize(900, 800);
        return panel;
    }

    public static void runView() {
        JFrame frame = new JFrame();
        frame.add(view.getDisplay());
        frame.setVisible(true);
        frame.setSize(400, 200);
        frame.setTitle("Forbidden Island > You lost!");
        frame.setResizable(false);
    }

    @Override
    public JComponent getDisplay() {
        final BufferedImage bufferedImage = ImageStorage.retrieveImage("loss_background.png");
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bufferedImage, 0, 0, null);
            }
        };
        JLabel winText = new JLabel("You lost!", JLabel.CENTER);
        return getComponent(panel, winText);
    }
}
