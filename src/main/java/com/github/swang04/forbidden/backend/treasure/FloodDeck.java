/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.board.TileType;
import com.github.swang04.forbidden.backend.players.Deck;

import java.util.ArrayList;
import java.util.List;

public class FloodDeck extends Deck<FloodCard> {

    private static FloodDeck floodDeck;

    public static FloodDeck getFloodDeck() {
        return floodDeck;
    }

    private final List<FloodCard> floodCards;

    public FloodDeck() {
        super();
        floodDeck = this;
        this.floodCards = new ArrayList<>();
        initializeFloodDeck();
        flood(6);
    }


}
