package com.github.swang04.forbidden.ui;

import com.github.swang04.forbidden.backend.board.Board;

import javax.swing.JComponent;

public abstract class Visualizer<T> {

    public static Visualizer<Board> getBoardVisualizer() {

    }

    public abstract JComponent visualize(T object);

}
