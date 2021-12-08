/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.treasure.Treasure;
import com.github.swang04.forbidden.ui.Visualizer;
import dev.kason.forbidden.ImageStorage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Set;

public class TreasureVisualizer extends Visualizer<Treasure> {

    private JPanel component;
    private final Set<Treasure> wonTreasures = PlayerManager.getInstance().getWonTreasures();

    public void updateComponent() {
        component.removeAll();
        for (Treasure wonTreasure : wonTreasures) {
            component.add(getForTreasure(wonTreasure));
        }
        GameVisualizer.getGameVisualizer().repaintPanels();
    }

    @Override
    public JComponent visualize(Treasure object) {
        component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setBackground(ViewManager.getTransparent());
        updateComponent();
        return component;
    }

    public JComponent getForTreasure(Treasure treasure) {
        JPanel panel = new JPanel();
        panel.add(Box.createHorizontalGlue());
        panel.add(Box.createVerticalGlue());
        BufferedImage image = Objects.requireNonNull(ImageStorage.retrieveImage(treasure.getImageFileName()));
        JButton button = new JButton(new ImageIcon(ViewManager.getScaledImage(image, 65, 80)));
        button.setBackground(Color.LIGHT_GRAY);
        panel.add(button);
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createHorizontalGlue());
        panel.setBackground(ViewManager.getTransparent());
        return panel;
    }
}
