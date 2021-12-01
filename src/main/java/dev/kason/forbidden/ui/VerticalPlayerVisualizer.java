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

public class VerticalPlayerVisualizer extends Visualizer<Player> {

    private static final VerticalPlayerVisualizer visualizer = new VerticalPlayerVisualizer();
    private final InventoryItemVisualizer inventoryItemVisualizer = InventoryItemVisualizer.getInstance();

    public static VerticalPlayerVisualizer getInstance() {
        return visualizer;
    }

    @Override
    public JComponent visualize(Player object) {
        List<InventoryItem> items = object.getInventoryItems();
        JPanel wrapper = new JPanel();
        JPanel panel = new JPanel();
        BoxLayout Vertical = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(Vertical);
        for (InventoryItem item : items) {
            panel.add(inventoryItemVisualizer.visualize(item));
        }
        wrapper.add(panel);
        return panel;
    }

}
