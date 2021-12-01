/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.formdev.flatlaf.ui.FlatBorder;
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
    private final Visualizer<Player> horizontal = HorizontalPlayerVisualizer.getInstance();
    private final Visualizer<Player> vertical = VerticalPlayerVisualizer.getInstance();

    public static GameVisualizer getInstance() {
        return gameVisualizer;
    }

    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public JComponent visualize(Game object) {
        JPanel panel = new JPanel();
        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);
        panel.add(boardUI.getDisplay(), BorderLayout.CENTER);
        JPanel bottom = new JPanel();
        bottom.add(floodDeckVisualizer.visualize(object.getBoard().getFloodDeck()));
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
            panel1.setAlignmentX(0.5f);
            panel1.setAlignmentY(0.5f);
            Object value = switch (index) {
                case 1 -> BorderLayout.NORTH;
                case 2 -> BorderLayout.WEST;
                case 3 -> BorderLayout.EAST;
                case 4 -> BorderLayout.SOUTH;
                default -> throw new IllegalStateException("More than 4 players: index = " + index);
            };
            if (index == 4) {
                bottom.add(component);
            } else {
                panel.add(panel1, value);
            }
        }
        bottom.setBorder(new FlatBorder());
        panel.add(bottom, BorderLayout.SOUTH);
        panel.repaint();
        return panel;
    }

}
