/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui.visualizers;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.ui.Visualizer;
import dev.kason.forbidden.logging.Log;

import javax.swing.JComponent;
import java.util.logging.Logger;

public class BoardVisualizer extends Visualizer<Board> {

    private static final BoardVisualizer visualizer = new BoardVisualizer();
    private static final Logger logger = Log.logger();

    public static BoardVisualizer getVisualizer() {
        return visualizer;
    }

    @Override
    public JComponent visualize(Board board) {
        logger.info("Hello wrodl");
        return null;
    }
}
