/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.ui;

import dev.kason.forbidden.ImageStorage;
import dev.kason.forbidden.ui.View;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static com.github.swang04.forbidden.ui.LossView.getComponent;

public class WinView extends View {

    private static final WinView view = new WinView();

    private WinView() {
        super("You Win!", "Win");
    }

    public static WinView init() {
        return view;
    }

    public static void runView() {
        JFrame frame = new JFrame();
        frame.setIconImage(ImageStorage.retrieveImage("icon_1.png"));
        frame.add(view.getDisplay());
        frame.setVisible(true);
        frame.setSize(400, 200);
        frame.setTitle("Forbidden Island > You win!");
        frame.setResizable(false);
    }

    @Override
    public JComponent getDisplay() {
        final BufferedImage bufferedImage = ImageStorage.retrieveImage("regular_background.png");
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bufferedImage, 0, 0, null);
            }
        };
        JLabel winText = new JLabel("YOU WIN!", JLabel.CENTER);
        return getComponent(panel, winText);
    }
}
