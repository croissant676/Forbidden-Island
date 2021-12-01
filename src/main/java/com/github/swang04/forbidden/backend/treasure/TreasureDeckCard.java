/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.players.Card;
import com.github.swang04.forbidden.backend.players.Player;

// Just an interface to represent a card that will be in a treasure deck
public interface TreasureDeckCard extends Card, InventoryItem {

    void onDraw(Player player);

}
