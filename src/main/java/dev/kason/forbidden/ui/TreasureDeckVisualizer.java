/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.players.Player;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.treasure.TreasureDeck;
import com.github.swang04.forbidden.backend.treasure.TreasureDeckCard;
import com.github.swang04.forbidden.backend.treasure.WatersRiseCard;
import com.github.swang04.forbidden.ui.Visualizer;
import dev.kason.forbidden.ImageStorage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class TreasureDeckVisualizer extends Visualizer<TreasureDeck> {

    private static final TreasureDeckVisualizer t = new TreasureDeckVisualizer();

    public static TreasureDeckVisualizer getInstance() {
        return t;
    }

    @Override
    public JComponent visualize(TreasureDeck object) {
        BufferedImage bufferedImage = ImageStorage.retrieveImage("treasure_card_back.png");
        bufferedImage = ViewManager.getScaledImage(bufferedImage, 70, 100);
        JButton button = new JButton(new ImageIcon(Objects.requireNonNull(bufferedImage)));
        button.addActionListener(e -> {
            Player player = PlayerManager.getInstance().getCurrentPlayer();
            TreasureDeckCard card = object.popTopCard();
            if (card instanceof WatersRiseCard riseCard) {
                riseCard.apply();
                BoardUI.getInstance().showTilesAndPawns();
                GameVisualizer.getInstance().repaintPanels();
            }
            player.receiveCard(PlayerManager.getInstance().validateCard(card));
            PlayerInventoryVisualizer.updateHand(player);
        });
        return button;
    }
}
