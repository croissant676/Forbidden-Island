/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.players.Player;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.ui.Visualizer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
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
        JLabel label = new JLabel(object.getName(), JLabel.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);
        for (InventoryItem item : items) {
            panel.add(inventoryItemVisualizer.visualize(item));
        }
        JButton button = new JButton("Give Card");
        button.addActionListener((e) -> {
            System.out.println("Calling thing for transfer to like " + object.getName());
            Player player = PlayerManager.getInstance().getCurrentPlayerTurn();
            if (object.equals(player)) {
                System.out.println("Code to prevent player from trading with themselves.");
            } else {
                System.out.println("From " + player.getName());
            }
        });
        panel.add(button);
        panel.setAlignmentX(0.5f);
        panel.setAlignmentY(0.5f);
        return panel;
    }

}
