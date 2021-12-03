/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.players.Player;

public class HeliLiftCard implements TreasureDeckCard {

    private Player player;

    @Override
    public Player getHolder() {
        return player;
    }

    @Override
    public void setHolder(Player player) {
        this.player = player;
    }

    @Override
    public void onDraw(Player player) {
    }


    @Override
    public String toString() {
        return "Heli-lift";
    }
}
