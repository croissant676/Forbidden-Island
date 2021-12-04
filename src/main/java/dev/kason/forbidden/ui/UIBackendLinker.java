/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.players.Player;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.treasure.InventoryItem;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;

public class UIBackendLinker {

    public static void establishClick(Tile tile, MouseEvent event) {
        InventoryItem card = PlayerManager.getCurrentlySelectedItem();
        if (event.getButton() == MouseEvent.BUTTON3) {

        }
    }

    public static void paintMovements() {
        Player player = PlayerManager.getInstance().getCurrentPlayer();
        List<Tile> tiles = player.getPawn().getPossibleTiles();
        for (Tile tile : tiles) {
            tile.getButton().setBackground(Color.YELLOW);
        }
        player.getPawn().getTile().getButton().setBackground(Color.RED);
    }

}
