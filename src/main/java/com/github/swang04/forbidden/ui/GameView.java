/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.ui;

import com.github.swang04.forbidden.backend.board.Board;
import dev.kason.forbidden.ui.BoardVisualizer;
import dev.kason.forbidden.ui.View;

import javax.swing.JComponent;

public class GameView extends View {

    private static final GameView view = new GameView();
    private final BoardVisualizer boardVisualizer = BoardVisualizer.getVisualizer();

    private GameView() {
        super("Game", false);
    }

    public static GameView getInstance() {
        return view;
    }

    @Override
    public JComponent getDisplay() {

        return boardVisualizer.visualize(Board.getInstance());
    }
}
