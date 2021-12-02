/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.ui;

import dev.kason.forbidden.ui.BoardUI;
import dev.kason.forbidden.ui.BoardVisualizer;
import dev.kason.forbidden.ui.GameVisualizer;
import dev.kason.forbidden.ui.PlayerInventoryVisualizer;

public class InterfaceUtilities {

    private static final BoardUI board = BoardUI.getInstance();
    private static final BoardVisualizer boardVisualizer = BoardVisualizer.getVisualizer();
    private static final GameVisualizer gameVisualizer = GameVisualizer.getInstance();
    private static final PlayerInventoryVisualizer vertical = PlayerInventoryVisualizer.getVertical();
    private static final PlayerInventoryVisualizer horizontal = PlayerInventoryVisualizer.getHorizontal();

    public static BoardUI getBoard() {
        return board;
    }
}
