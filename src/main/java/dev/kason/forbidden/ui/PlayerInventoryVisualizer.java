/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.players.Player;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.backend.treasure.TreasureDeckCard;
import com.github.swang04.forbidden.ui.Visualizer;
import org.jetbrains.annotations.NotNull;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        glueItem(items, panel);
        panel.repaint();
        GameVisualizer.getInstance().repaintPanels();
    }

    private static void glueItem(List<InventoryItem> items, JPanel panel) {
        for (InventoryItem item : items) {
            JPanel panel1 = new JPanel();
            panel1.add(Box.createHorizontalGlue());
            panel1.add(Box.createVerticalGlue());
            panel1.add(inventoryItemVisualizer.visualize(item));
            panel1.add(Box.createHorizontalGlue());
            panel1.add(Box.createVerticalGlue());
            panel1.setBackground(ViewManager.getTransparent());
            panel.add(panel1);
        }
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
        JPanel p = new JPanel();
        JLabel label = new JLabel(object.getPlayerType().getName(), JLabel.CENTER);
        label.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        label.setForeground(object.getPlayerType().getRepresentingColor());
        label.setHorizontalAlignment(JLabel.CENTER);
        p.add(Box.createHorizontalGlue());
        p.add(Box.createVerticalGlue());
        p.add(label);
        p.add(Box.createHorizontalGlue());
        p.add(Box.createVerticalGlue());
        if (value == BoxLayout.X_AXIS) {
            wrapper.add(Box.createHorizontalStrut(10));
        } else {
            wrapper.add(Box.createVerticalStrut(10));
        }
        p.setBackground(ViewManager.getTransparent());
        glueItem(items, panel);
        panel.setBackground(ViewManager.getTransparent());
        componentMap.put(object.getName(), panel);
        JPanel bp = new JPanel();
        bp.add(Box.createHorizontalGlue());
        bp.add(Box.createVerticalGlue());
        JButton button = new JButton("Give");
        button.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        button.addActionListener((e) -> {
            System.out.println("Calling thing for transfer to like " + object.getName());
            Player player = PlayerManager.getInstance().getCurrentPlayer();
            if (object.equals(player)) {
                JOptionPane.showMessageDialog(null, "You can't trade with yourself!", "Forbidden Island > Trade with self", JOptionPane.WARNING_MESSAGE);
            } else {
                player.transferCard(object, (TreasureDeckCard) PlayerManager.getCurrentlySelectedItem());
            }
        });
        bp.add(button);
        bp.add(Box.createHorizontalGlue());
        bp.add(Box.createVerticalGlue());
        bp.setBackground(ViewManager.getTransparent());
        panel.setAlignmentX(0.5f);
        panel.setAlignmentY(0.5f);
        wrapper.add(p);
        wrapper.add(panel);
        wrapper.setAlignmentX(0.5f);
        wrapper.setAlignmentY(0.5f);
        if (value == BoxLayout.X_AXIS) {
            wrapper.add(Box.createHorizontalStrut(10));
        } else {
            wrapper.add(Box.createVerticalStrut(10));
        }
        wrapper.add(bp);
        return wrapper;
    }
}
