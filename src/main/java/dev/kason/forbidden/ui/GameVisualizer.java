/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.players.Player;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.ui.Visualizer;
import dev.kason.forbidden.ImageStorage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Set;

public class GameVisualizer extends Visualizer<Game> {

    private static final GameVisualizer gameVisualizer = new GameVisualizer();
    private final BoardUI boardUI = BoardUI.getInstance();
    private final BoardVisualizer visualizer = BoardVisualizer.getVisualizer();
    private final FloodDeckVisualizer floodDeckVisualizer = FloodDeckVisualizer.getInstance();
    private final PlayerInventoryVisualizer horizontal = PlayerInventoryVisualizer.getHorizontal();
    private final PlayerInventoryVisualizer vertical = PlayerInventoryVisualizer.getVertical();
    private final TreasureDeckVisualizer treasureDeckVisualizer = TreasureDeckVisualizer.getInstance();
    private JPanel gameWrapper;
    private JPanel gameBase;
    private final JPanel selectedCPanel = new JPanel();
    private JPanel gameCardsPanel;

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

    public JPanel getGameCardsPanel() {
        return gameCardsPanel;
    }

    public void repaintPanels() {
        gameBase.repaint();
        gameWrapper.repaint();
        JFrame frame = BoardVisualizer.getFrame();
        int width = frame.getWidth();
        int height = frame.getHeight();
        frame.setSize(width + 1, height);
        frame.setSize(width, height);
    }

    public void updateSelectedItemComponent() {
        InventoryItem currentlySelectedItem = PlayerManager.getCurrentlySelectedItem();
        selectedCPanel.removeAll();
        JButton component = (JButton) InventoryItemVisualizer.getInstance().visualize(currentlySelectedItem);
        ActionListener[] actionListeners = component.getActionListeners();
        if (actionListeners.length > 0) {
            component.removeActionListener(actionListeners[0]);
        }
        selectedCPanel.add(component);
        repaintPanels();
    }

    @Override
    public JComponent visualize(Game object) {
        final BufferedImage image = ViewManager.getScaledImage(ImageStorage.retrieveImage("table_background.png"), 1500, 1000);
        gameWrapper = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        gameBase = new JPanel();
        gameBase.setBackground(ViewManager.getTransparent());
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
        gameCardsPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(gameCardsPanel, BoxLayout.Y_AXIS);
        gameCardsPanel.setLayout(boxLayout);
        gameCardsPanel.add(floodDeckVisualizer.visualize(object.getBoard().getFloodDeck()));
        gameCardsPanel.add(Box.createVerticalStrut(10));
        gameCardsPanel.add(treasureDeckVisualizer.visualize(object.getPlayerManager().getDeck()));
        gameCardsPanel.add(Box.createVerticalStrut(10));
        gameCardsPanel.setBackground(ViewManager.getTransparent());
        JButton button = new JButton("Discard");
        button.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
        button.setBackground(new Color(253, 61, 31));
        button.addActionListener((e) -> {
            InventoryItem item = PlayerManager.getCurrentlySelectedItem();
            if (item == null) {
                JOptionPane.showMessageDialog(null, "No card is selected right now.");
            }
        });
        button.setSize(70, 100);
        gameWrapper.add(selectedCPanel);
        updateSelectedItemComponent();
        gameCardsPanel.add(button);
        gameWrapper.add(gameCardsPanel);
        gameWrapper.add(gameBase);
        gameBase.repaint();
        return gameWrapper;
    }

}
