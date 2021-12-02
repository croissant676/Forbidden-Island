/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.players.Player;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.ui.Visualizer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Set;

public class GameVisualizer extends Visualizer<Game> {

    private static final GameVisualizer gameVisualizer = new GameVisualizer();
    private final BoardUI boardUI = BoardUI.getInstance();
    private final BoardVisualizer visualizer = BoardVisualizer.getVisualizer();
    private final FloodDeckVisualizer floodDeckVisualizer = FloodDeckVisualizer.getInstance();
    private final PlayerInventoryVisualizer horizontal = PlayerInventoryVisualizer.getHorizontal();
    private final PlayerInventoryVisualizer vertical = PlayerInventoryVisualizer.getVertical();
    private JPanel gameWrapper;
    private JPanel gameBase;

    public static GameVisualizer getInstance() {
        return gameVisualizer;
    }

    public BoardUI getBoardUI() {
        return boardUI;
    }

    public JPanel getGameWrapper() {
        return gameWrapper;
    }

    public JPanel getGameBase() {
        return gameBase;
    }

    @Override
    public JComponent visualize(Game object) {
        gameWrapper = new JPanel();
        gameBase = new JPanel();
        BorderLayout layout = new BorderLayout();
        gameBase.setLayout(layout);
        gameBase.add(boardUI.getDisplay(), BorderLayout.CENTER);
        Set<Player> playerSet = PlayerManager.getInstance().getPlayers();
        int index = 0;
        for (Player player : playerSet) {
            JComponent component;
            JPanel panel1 = new JPanel();
            if ((index++ & 1) == 0) {
                component = horizontal.visualize(player);
            } else {
                component = vertical.visualize(player);
            }
            panel1.add(component);
            Object value = switch (index) {
                case 1 -> BorderLayout.PAGE_START;
                case 2 -> BorderLayout.LINE_START;
                case 4 -> BorderLayout.LINE_END;
                case 3 -> BorderLayout.PAGE_END;
                default -> throw new IllegalStateException("More than 4 players: index = " + index);
            };
            gameBase.add(panel1, value);
        }
        gameWrapper.add(floodDeckVisualizer.visualize(object.getBoard().getFloodDeck()));
        gameWrapper.add(gameBase);
        gameBase.repaint();
        return gameWrapper;
    }

}
