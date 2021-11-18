/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui.visualizers;

import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.board.TileState;
import com.github.swang04.forbidden.backend.board.TileType;
import com.github.swang04.forbidden.ui.Visualizer;

import javax.swing.JComponent;

public class TileVisualizer extends Visualizer<Tile> {
    @Override
    public JComponent visualize(Tile object) {
        TileType tileType = object.getTileType();
        TileState state = object.getTileState();
        if (state == TileState.SUNK) {
            return null;
        } else if (state == TileState.DRY) {

        }
        return null;
    }
}
