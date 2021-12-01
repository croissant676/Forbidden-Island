/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.players.Deck;

public class FloodDeck extends Deck<FloodCard> {

    private static FloodDeck floodDeck;

    public static FloodDeck getFloodDeck() {
        return floodDeck;
    }

    private final Board board;

    public FloodDeck() {
        super();
        floodDeck = this;
        this.board = Board.getInstance();
    }


}
