/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.ui.Visualizer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class BoardVisualizer extends Visualizer<Board> {

    private static final BoardVisualizer visualizer = new BoardVisualizer();
    public static BoardVisualizer getVisualizer() {
        return visualizer;
    }

    @Override
    public JComponent visualize(Board board) {
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout();
        panel.setLayout(layout);
        return panel;
    }
}
