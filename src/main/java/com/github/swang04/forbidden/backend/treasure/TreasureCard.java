/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.players.Player;

// TDC that isn't special: Just a treasure card - Sandbags, Heli-Lifts, and Waters Rises
public class TreasureCard implements TreasureDeckCard {

    private Treasure representingTreasure;

    @Override
    public void onDraw(Player player) {
        player.receiveCard(this);
    }

    public Treasure getRepresentingTreasure() {
        return representingTreasure;
    }
}
