/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.players.Player;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.ui.Visualizer;
import org.jetbrains.annotations.NotNull;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerInventoryVisualizer extends Visualizer<Player> {

    private static final PlayerInventoryVisualizer horizontal = new PlayerInventoryVisualizer(BoxLayout.X_AXIS);
    private static final PlayerInventoryVisualizer vertical = new PlayerInventoryVisualizer(BoxLayout.Y_AXIS);
    private static final InventoryItemVisualizer inventoryItemVisualizer = InventoryItemVisualizer.getInstance();
    private final int value;

    public PlayerInventoryVisualizer(int value) {
        this.value = value;
    }

    public static PlayerInventoryVisualizer getVertical() {
        return vertical;
    }

    public static PlayerInventoryVisualizer getHorizontal() {
        return horizontal;
    }

    public InventoryItemVisualizer getInventoryItemVisualizer() {
        return inventoryItemVisualizer;
    }

    private static final Map<String, JPanel> componentMap = new HashMap<>();

    public static JComponent getHandComponentFor(@NotNull Player player) {
        return componentMap.get(player.getName());
    }

    public static void updateHand(Player player) {
        JPanel panel = componentMap.get(player.getName());
        List<InventoryItem> items = player.getInventoryItems();
        panel.removeAll();
        for (InventoryItem item : items) {
            panel.add(inventoryItemVisualizer.visualize(item));
        }
        panel.repaint();
        GameVisualizer.getInstance().repaintPanels();
    }

    @Override
    public JComponent visualize(Player object) {
        JPanel wrapper = new JPanel();
        wrapper.setBackground(ViewManager.getTransparent());
        BoxLayout layout = new BoxLayout(wrapper, value);
        wrapper.setLayout(layout);
        List<InventoryItem> items = object.getInventoryItems();
        JPanel panel = new JPanel();
        BoxLayout horizontal = new BoxLayout(panel, value);
        panel.setLayout(horizontal);
        JLabel label = new JLabel(object.getPlayerType().getName(), JLabel.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        wrapper.add(label);
        if (value == BoxLayout.X_AXIS) {
            wrapper.add(Box.createHorizontalStrut(10));
        } else {
            wrapper.add(Box.createVerticalStrut(10));
        }
        for (InventoryItem item : items) {
            panel.add(inventoryItemVisualizer.visualize(item));
        }
        componentMap.put(object.getName(), panel);
        JButton button = new JButton("Give Card");
        button.addActionListener((e) -> {
            System.out.println("Calling thing for transfer to like " + object.getName());
            Player player = PlayerManager.getInstance().getCurrentPlayer();
            if (object.equals(player)) {
                System.out.println("Code to prevent player from trading with themselves.");
            } else {
                System.out.println("From " + player.getName());
            }
        });
        panel.setAlignmentX(0.5f);
        panel.setAlignmentY(0.5f);
        wrapper.add(panel);
        if (value == BoxLayout.X_AXIS) {
            wrapper.add(Box.createHorizontalStrut(10));
        } else {
            wrapper.add(Box.createVerticalStrut(10));
        }
        wrapper.add(button);
        return wrapper;
    }
}
