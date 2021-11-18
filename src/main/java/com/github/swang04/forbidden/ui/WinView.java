/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.ui;

import dev.kason.forbidden.ui.View;
import dev.kason.forbidden.ui.ViewManager;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class WinView extends View {

    private static final WinView view = new WinView();

    private WinView() {
        super("You Win!", "Win");
    }

    public static WinView init() {
        return view;
    }

    @Override
    public JComponent getDisplay() {
        JPanel panel = new JPanel();
        JLabel winText = new JLabel("YOU WIN!", JLabel.CENTER);
        winText.setFont(new Font("Serif", Font.BOLD, 70));
        panel.add(winText);
        panel.setSize(900, 800);
        ViewManager.display(MenuView.init());
        return panel;
    }
}
