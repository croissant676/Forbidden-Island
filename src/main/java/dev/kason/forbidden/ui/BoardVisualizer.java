/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.ui.Visualizer;
import dev.kason.forbidden.ImageStorage;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class BoardVisualizer extends Visualizer<Board> {

    private static final BoardVisualizer visualizer = new BoardVisualizer();

    public static BoardVisualizer getVisualizer() {
        return visualizer;
    }
    private static JFrame frame;
    private final BoardUI ui = new BoardUI();

    public static JFrame getFrame() {
        return frame;
    }

    public static void createBoardUI() {
        FlatDarkLaf.setup();
        frame = new JFrame("Forbidden Isl`and > Game");
        frame.setIconImage(ImageStorage.retrieveImage("icon_1.png"));
        frame.setSize(1400, 900);
        JComponent component = GameVisualizer.getInstance().visualize(Game.getGame());
        frame.add(component);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    @Override
    public JComponent visualize(Board board) {
        ui.matchGame();
        return ui.getDisplay();
    }
}
