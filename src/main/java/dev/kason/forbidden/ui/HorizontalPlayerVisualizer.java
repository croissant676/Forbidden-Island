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
import java.util.List;

public class HorizontalPlayerVisualizer extends Visualizer<Player> {

    private static final HorizontalPlayerVisualizer visualizer = new HorizontalPlayerVisualizer();
    private final InventoryItemVisualizer inventoryItemVisualizer = InventoryItemVisualizer.getInstance();

    public static HorizontalPlayerVisualizer getInstance() {
        return visualizer;
    }

    @Override
    public JComponent visualize(Player object) {
        List<InventoryItem> items = object.getInventoryItems();
        JPanel wrapper = new JPanel();
        JPanel panel = new JPanel();
        BoxLayout horizontal = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(horizontal);
        for (InventoryItem item : items) {
            panel.add(inventoryItemVisualizer.visualize(item));
        }
        wrapper.add(panel);
        return panel;
    }

}
