/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.TileType;
import com.github.swang04.forbidden.backend.board.WaterMeter;
import com.github.swang04.forbidden.backend.players.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloodDeck extends Deck<FloodCard> {

    private static FloodDeck floodDeck;

    public static FloodDeck getFloodDeck() {
        return floodDeck;
    }

    private final List<FloodCard> discardedFloodCards;

    public FloodDeck() {
        super();
        floodDeck = this;
        this.discardedFloodCards = new ArrayList<>();
        initializeFloodDeck();
        flood(6);
    }


}
