/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.players.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Iterator;

public class FloodDeck extends Deck<FloodCard> {

    private static FloodDeck floodDeck;
    private ArrayDeque<FloodCard> floodCards;
    private ArrayDeque<FloodCard> drawnCards;

    public FloodDeck(Board board) {
        floodDeck = this;

    }

    public static FloodDeck getFloodDeck() {
        return floodDeck;
    }

    public FloodCard getTopCard() {
        return floodCards.pop();
    }

    public FloodCard drawTopCardAndUse() {
        FloodCard card = floodCards.pop();
        drawnCards.add(card);

        return card;
    }

    public ArrayDeque<FloodCard> getFloodCards() {
        return floodCards;
    }

    @NotNull
    @Override
    public Iterator<FloodCard> iterator() {
        return floodCards.iterator();
    }
}
