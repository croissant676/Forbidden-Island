package game;

import utils.FullyImplemented;
import javax.swing.JComponent;

/** Represents a game element that can be visualized,
 * or displayed on the screen. Nearly everything
 * important implements this interface, such as
 * <tt>Tile</tt>, <tt>Board</tt>, and <tt>Water Meter</tt>.
 * @since 1.0.0
 * @author Kason
 * @see game.board.Tile
 * @see game.board.Board
 * @see game.board.WaterMeter
 * */
@FunctionalInterface
@FullyImplemented
public interface Visualizable {

    /** Returns a visual representation of this element.
     * @return A visual representation of this element.
     * @since 1.0.0
     * */
    JComponent getVisual();

}
