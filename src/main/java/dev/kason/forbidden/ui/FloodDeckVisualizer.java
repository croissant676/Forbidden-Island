/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.treasure.FloodDeck;
import com.github.swang04.forbidden.ui.Visualizer;
import dev.kason.forbidden.ImageStorage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class FloodDeckVisualizer extends Visualizer<FloodDeck> {

    private static final FloodDeckVisualizer visualizer = new FloodDeckVisualizer();

    private FloodDeckVisualizer() {
    }

    public static FloodDeckVisualizer getInstance() {
        return visualizer;
    }

    @Override
    public JComponent visualize(FloodDeck object) {
        BufferedImage bufferedImage = ImageStorage.retrieveImage("flood_card_back.png");
        bufferedImage = ViewManager.getScaledImage(bufferedImage, 70, 100);
        JButton button = new JButton(new ImageIcon(Objects.requireNonNull(bufferedImage)));
        button.addActionListener(e -> {
            object.floodTopCard();
            BoardUI.getInstance().updateTilesOnly();
        });
        return button;
    }
}
