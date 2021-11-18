/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.ui;

import dev.kason.forbidden.ui.View;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class LossView extends View {

    private static final LossView view = new LossView();

    private LossView() {
        super("You lost!", "Lost");
    }

    public static LossView init() {
        return view;
    }

    @Override
    public JComponent getDisplay() {
        JPanel panel = new JPanel();
        JLabel lossText = new JLabel("YOU LOSE!", JLabel.CENTER);
        lossText.setFont(new Font("Serif", Font.BOLD, 70));
        panel.add(lossText);
        return panel;
    }
}
