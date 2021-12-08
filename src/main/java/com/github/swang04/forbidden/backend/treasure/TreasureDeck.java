/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.players.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreasureDeck extends Deck<TreasureDeckCard> {

    private final List<TreasureDeckCard> discard = new ArrayList<>();

    public TreasureDeck() {
        super();
        initializeTreasureDeck();
    }

    private void initializeTreasureDeck() {
        for (Treasure value : Treasure.values()) {
            for (int count = 0; count < 5; count++) {
                addCard(new TreasureCard(value));
            }
        }
        for (int count = 0; count < 2; count++) {
            addCard(new SandbagsCard());
        }
        for (int count = 0; count < 3; count++) {
            addCard(new HeliLiftCard());
        }
        for (int count = 0; count < 3; count++) {
            addCard(new WatersRiseCard());
        }
        shuffle();
    }

    public List<TreasureDeckCard> getDiscard() {
        return discard;
    }

    @Override
    public TreasureDeckCard popTopCard() {
        if(deque.isEmpty()) {
            Collections.shuffle(discard);
            addCards(discard);
        }
        return super.popTopCard();
    }
}
