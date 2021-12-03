/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.players.Player;

// TDC that isn't special: Just a treasure card - Sandbags, Heli-Lifts, and Waters Rises
public class TreasureCard implements TreasureDeckCard {

    public static boolean isSandbags(TreasureDeckCard treasureDeckCard) {
        return treasureDeckCard instanceof SandbagsCard;
    }

    public static boolean isHeliLift(TreasureDeckCard treasureDeckCard) {
        return treasureDeckCard instanceof HeliLiftCard;
    }

    public static boolean isWatersRise(TreasureDeckCard treasureDeckCard) {
        return treasureDeckCard instanceof WatersRiseCard;
    }

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
    public String toString() {
        return "Treasure[" + representingTreasure.name() + "]";
    }
}
