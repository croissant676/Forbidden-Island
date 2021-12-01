/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.players.Player;

// TDC that isn't special: Just a treasure card - Sandbags, Heli-Lifts, and Waters Rises
public class TreasureCard implements TreasureDeckCard {

    private final Treasure representingTreasure;
    private Player holdingPlayer;

    public TreasureCard(Treasure representingTreasure) {
        this.representingTreasure = representingTreasure;
    }

    @Override
    public void onDraw(Player player) {
        player.receiveCard(this);
    }

    public Treasure getRepresentingTreasure() {
        return representingTreasure;
    }

    @Override
    public Player getHolder() {
        return holdingPlayer;
    }

    @Override
    public void setHolder(Player player) {

    }

    @Override
    public String toString() {
        return "Treasure[" + representingTreasure.name() + "]";
    }
}
