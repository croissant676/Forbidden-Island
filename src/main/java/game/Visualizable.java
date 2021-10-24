package game;

import javax.swing.*;

/**
 * Represents a game element that can be visualized,
 * or displayed on the screen. Nearly everything
 * important implements this interface, such as
 * <tt>Tile</tt>, <tt>Board</tt>, and <tt>Water Meter</tt>.
 *
 * @author Kason
 * @see game.board.Tile
 * @see game.board.Board
 * @see game.board.WaterMeter
 * @since 1.0.0
 */
@FunctionalInterface
public interface Visualizable {

    /**
     * Returns a visual representation of this element.
     *
     * @return A visual representation of this element.
     * @since 1.0.0
     */
    JComponent getVisual();

}
