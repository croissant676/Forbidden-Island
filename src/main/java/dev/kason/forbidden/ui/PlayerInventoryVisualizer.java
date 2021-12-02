/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.players.Player;
import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.ui.Visualizer;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.List;

public class PlayerInventoryVisualizer extends Visualizer<Player> {

    private static final PlayerInventoryVisualizer horizontal = new PlayerInventoryVisualizer(BoxLayout.X_AXIS);
    private static final PlayerInventoryVisualizer vertical = new PlayerInventoryVisualizer(BoxLayout.Y_AXIS);
    private final InventoryItemVisualizer inventoryItemVisualizer = InventoryItemVisualizer.getInstance();
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

    @Override
    public JComponent visualize(Player object) {
        List<InventoryItem> items = object.getInventoryItems();
        JPanel panel = new JPanel();
        BoxLayout horizontal = new BoxLayout(panel, value);
        panel.setLayout(horizontal);
        for (InventoryItem item : items) {
            panel.add(inventoryItemVisualizer.visualize(item));
        }
        panel.setAlignmentX(0.5f);
        panel.setAlignmentY(0.5f);
        panel.setBackground(Color.GRAY);
        return panel;
    }

}
