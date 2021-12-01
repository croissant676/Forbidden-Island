/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.ui;

import com.github.swang04.forbidden.backend.board.Board;
import dev.kason.forbidden.ui.BoardVisualizer;

import javax.swing.JComponent;

public abstract class Visualizer<T> {

    public static Visualizer<Board> getBoardVisualizer() {
        return BoardVisualizer.getVisualizer();
    }

    public abstract JComponent visualize(T object);

}
