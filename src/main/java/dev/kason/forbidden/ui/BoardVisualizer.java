/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.ui.Visualizer;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class BoardVisualizer extends Visualizer<Board> {

    private static final BoardVisualizer visualizer = new BoardVisualizer();

    public static BoardVisualizer getVisualizer() {
        return visualizer;
    }

    private final BoardUI ui = new BoardUI();

    public static void runTest() {
        FlatDarkLaf.setup();
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setVisible(true);
        JComponent component = GameVisualizer.getInstance().visualize(Game.getGame());
        frame.add(component);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public JComponent visualize(Board board) {
        ui.matchGame();
        return ui.getDisplay();
    }
}
