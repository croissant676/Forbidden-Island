/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.treasure.HeliLiftCard;
import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.backend.treasure.SandbagsCard;
import com.github.swang04.forbidden.backend.treasure.Treasure;
import com.github.swang04.forbidden.backend.treasure.TreasureCard;
import com.github.swang04.forbidden.backend.treasure.WatersRiseCard;
import com.github.swang04.forbidden.ui.Visualizer;
import dev.kason.forbidden.ImageStorage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class InventoryItemVisualizer extends Visualizer<InventoryItem> {

    private static final InventoryItemVisualizer v = new InventoryItemVisualizer();

    public static InventoryItemVisualizer getInstance() {
        return v;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public JComponent visualize(InventoryItem object) {
        if (object instanceof TreasureCard treasureCard) {
            Treasure representingTreasure = treasureCard.getRepresentingTreasure();
            JButton button = new JButton(new ImageIcon(ViewManager.getScaledImage(representingTreasure.getImage(), 55, 80)));
            return button;
        } else if (object instanceof SandbagsCard) {
            BufferedImage bufferedImage = ImageStorage.retrieveImage("card_sandbags.png");
            JButton button = new JButton(new ImageIcon(Objects.requireNonNull(ViewManager.getScaledImage(bufferedImage, 55, 80))));
            return button;
        } else if (object instanceof HeliLiftCard) {
            BufferedImage bufferedImage = ImageStorage.retrieveImage("card_helilift.png");
            JButton button = new JButton(new ImageIcon(Objects.requireNonNull(ViewManager.getScaledImage(bufferedImage, 55, 80))));
            return button;
        } else if (object instanceof WatersRiseCard) {
            BufferedImage bufferedImage = ImageStorage.retrieveImage("card_waters_rise.png");
            JButton button = new JButton(new ImageIcon(Objects.requireNonNull(ViewManager.getScaledImage(bufferedImage, 55, 80))));
            return button;
        } else {
            return null;
        }
    }
}
