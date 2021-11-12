package dev.kason.forbidden.ui.visualizers;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.ui.Visualizer;

import javax.swing.JComponent;

public class BoardVisualizer extends Visualizer<Board> {

    private static final BoardVisualizer visualizer = new BoardVisualizer();

    public static BoardVisualizer getVisualizer() {
        return visualizer;
    }

    @Override
    public JComponent visualize(Board board) {
        return null;
    }
}
