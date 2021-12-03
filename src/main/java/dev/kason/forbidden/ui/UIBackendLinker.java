/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.treasure.InventoryItem;

public class UIBackendLinker {

    public static void establishClick(Tile tile) {
        InventoryItem card = PlayerManager.getCurrentlySelectedItem();

    }

}
