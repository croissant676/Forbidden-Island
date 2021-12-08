/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.players.Player;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.players.PlayerType;
import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.backend.treasure.Treasure;
import com.github.swang04.forbidden.backend.treasure.TreasureCard;
import com.github.swang04.forbidden.backend.treasure.TreasureDeckCard;
import com.github.swang04.forbidden.ui.Visualizer;
import dev.kason.forbidden.ImageStorage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;

public class GameVisualizer extends Visualizer<Game> {

    private static final GameVisualizer gameVisualizer = new GameVisualizer();
    private final BoardUI boardUI = BoardUI.getInstance();
    private final BoardVisualizer visualizer = BoardVisualizer.getVisualizer();
    private final FloodDeckVisualizer floodDeckVisualizer = FloodDeckVisualizer.getInstance();
    private final PlayerInventoryVisualizer horizontal = PlayerInventoryVisualizer.getHorizontal();
    private final PlayerInventoryVisualizer vertical = PlayerInventoryVisualizer.getVertical();
    private final TreasureDeckVisualizer treasureDeckVisualizer = TreasureDeckVisualizer.getInstance();
    private final TreasureVisualizer treasureVisualizer = new TreasureVisualizer();
    private final JLabel label = new JLabel("Selected:");
    private final JLabel currentPlayerLabel = new JLabel();
    private final JPanel selectedCPanel = new JPanel();
    private JPanel gameWrapper;
    private JPanel gameBase;
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

    public static GameVisualizer getGameVisualizer() {
        return gameVisualizer;
    }

    public BoardVisualizer getVisualizer() {
        return visualizer;
    }

    public JLabel getCurrentPlayerLabel() {
        return currentPlayerLabel;
    }

    public JLabel getLabel() {
        return label;
    }

    public FloodDeckVisualizer getFloodDeckVisualizer() {
        return floodDeckVisualizer;
    }

    public PlayerInventoryVisualizer getHorizontal() {
        return horizontal;
    }

    public PlayerInventoryVisualizer getVertical() {
        return vertical;
    }

    public TreasureDeckVisualizer getTreasureDeckVisualizer() {
        return treasureDeckVisualizer;
    }

    public JPanel getSelectedCPanel() {
        return selectedCPanel;
    }

    public TreasureVisualizer getTreasureVisualizer() {
        return treasureVisualizer;
    }

    public void setGameWrapper(JPanel gameWrapper) {
        this.gameWrapper = gameWrapper;
    }

    public void setGameBase(JPanel gameBase) {
        this.gameBase = gameBase;
    }

    public void setGameCardsPanel(JPanel gameCardsPanel) {
        this.gameCardsPanel = gameCardsPanel;
    }

    public WaterMeterVisualizer getWaterMeterVisualizer() {
        return waterMeterVisualizer;
    }

    public void repaintPanels() {
        gameBase.repaint();
        gameWrapper.repaint();
        JFrame frame = BoardVisualizer.getFrame();
        int width = frame.getWidth();
        int height = frame.getHeight();
        waterMeterVisualizer.updatePanel();
        frame.setSize(width + 1, height);
        frame.setSize(width, height);
    }

    private final WaterMeterVisualizer waterMeterVisualizer = new WaterMeterVisualizer();

    public void updateSelectedItemComponent() {
        InventoryItem currentlySelectedItem = PlayerManager.getCurrentlySelectedItem();
        selectedCPanel.removeAll();
        selectedCPanel.add(label);
        selectedCPanel.setBackground(ViewManager.getTransparent());
        JButton component = (JButton) InventoryItemVisualizer.getInstance().visualize(currentlySelectedItem);
        ActionListener[] actionListeners = component.getActionListeners();
        if (actionListeners.length > 0) {
            component.removeActionListener(actionListeners[0]);
        }
        selectedCPanel.add(component);
        repaintPanels();
    }

    public void updateCurPlayerLabel() {
        PlayerType playerType = PlayerManager.getInstance().getCurrentPlayer().getPlayerType();
        currentPlayerLabel.setText(playerType.getName() + " - " + PlayerManager.getInstance().getNumberOfActionsLeft());
        currentPlayerLabel.setForeground(playerType.getRepresentingColor());
    }

    @Override
    public JComponent visualize(Game object) {
        label.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        currentPlayerLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
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
                JOptionPane.showMessageDialog(null,
                        "No card is selected right now.",
                        "Forbidden Island > Discard Card",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            Player player = item.getHolder();
            PlayerManager.getInstance().discardOf((TreasureDeckCard) item);
            PlayerManager.setDefaultItem();
            PlayerInventoryVisualizer.updateHand(player);
            GameVisualizer.getInstance().updateSelectedItemComponent();
        });
        JButton skip = new JButton("Skip Turn");
        skip.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
        skip.setBackground(new Color(253, 61, 31));
        skip.addActionListener((e) -> {
            PlayerManager.getInstance().nextTurn();
            updateSelectedItemComponent();
            updateCurPlayerLabel();
            repaintPanels();
        });
        JButton gatherTreasureButton = new JButton("Gather treasure");
        gatherTreasureButton.setBackground(new Color(30, 144, 255));
        gatherTreasureButton.addActionListener((e) -> {
            Player player = PlayerManager.getInstance().getCurrentPlayer();
            Tile tile = player.getPawn().getTile();
            Treasure treasure = tile.getTreasure();
            if(treasure == null) {
                JOptionPane.showMessageDialog(null, "Tile " + tile.getEffectiveName() + " does not have a treasure within it", "Forbidden Island > No Treasure", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List<InventoryItem> items = player.getInventoryItems();
            if(validForTreasureTaking(items, treasure)) {
                treasure.setTakenYet(true);
                int count = 0;
                for (int i = 0; i < items.size() && count < 4; i++) {
                    InventoryItem item = items.get(i);
                    if(item instanceof TreasureCard card) {
                        if(card.getRepresentingTreasure() == treasure) {
                            items.remove(i);
                            count++;
                            i--;
                        }
                    }
                }
                PlayerInventoryVisualizer.updateHand(player);
                PlayerManager.getInstance().getWonTreasures().add(treasure);
                PlayerManager.getInstance().decrementActionsLeft();
                treasureVisualizer.updateComponent();
            } else {
                JOptionPane.showMessageDialog(null, "You do not have the cards required to gather treasure " + treasure.getFormalName() + ".", "Forbidden Island > Failed Treasure", JOptionPane.WARNING_MESSAGE);
            }
        });
        gatherTreasureButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
        button.setSize(70, 100);
        gameCardsPanel.add(selectedCPanel);
        updateCurPlayerLabel();
        gameCardsPanel.add(currentPlayerLabel);
        updateSelectedItemComponent();
        UIBackendLinker.paintMovements();
        gameCardsPanel.add(button);
        gameCardsPanel.add(skip);
        gameCardsPanel.add(gatherTreasureButton);
        gameCardsPanel.add(treasureVisualizer.visualize(null));
        gameWrapper.add(waterMeterVisualizer.visualize(Board.getInstance().getWaterMeter()));
        gameWrapper.add(gameCardsPanel);
        gameWrapper.add(gameBase);
        gameBase.repaint();
        return gameWrapper;
    }

    private static boolean validForTreasureTaking(List<InventoryItem> items, Treasure treasure) {
        int count = 0;
        for (InventoryItem item : items) {
            if(item instanceof TreasureCard card) {
                if(card.getRepresentingTreasure() == treasure) {
                    count++;
                }
            }
        }
        return count >= 4;
    }

}
