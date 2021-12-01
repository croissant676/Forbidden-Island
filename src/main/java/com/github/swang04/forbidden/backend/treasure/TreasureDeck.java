/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.players.Deck;

public class TreasureDeck extends Deck<TreasureDeckCard> {

    public TreasureDeck() {
        super();
        initializeTreasureDeck();
    }

    private void initializeTreasureDeck() {
        for (int count = 0; count < 2; count++) {
            addCard(new SandbagsCard());
        }
        for (int count = 0; count < 3; count++) {
            addCard(new HeliLiftCard());
        }
        for (int count = 0; count < 3; count++) {
            addCard(new WatersRiseCard());
        }
        for (Treasure value : Treasure.values()) {
            for (int count = 0; count < 5; count++) {
                addCard(new TreasureCard(value));
            }
        }
        shuffle();
    }

}
