/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.players.Player;

public class WatersRiseCard implements TreasureDeckCard {
    @Override
    public Player getHolder() {
        return null;
    }

    @Override
    public void setHolder(Player player) {

    }

    @Override
    public void onDraw(Player player) {

    }

    @Override
    public String toString() {
        return "Waters Rise";
    }
}
