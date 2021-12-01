/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.board.TileType;
import com.github.swang04.forbidden.backend.players.Card;

public class FloodCard implements Card {

    private final Tile tile;
    private final FloodDeck deck;

    public FloodCard(FloodDeck floodDeck, TileType tileType) {
        tile = Board.getInstance().getTileFor(tileType);
        this.deck = floodDeck;
    }

    public Tile getTile() {
        return tile;
    }

    public void use() {
        tile.progressFlooding();
    }

    public TileType getTileType() {
        return tile.getTileType();
    }

    public String getFileLocation() {
        return getTileType().name().toLowerCase() + "_flood_card.png";
    }

    public FloodDeck getDeck() {
        return deck;
    }

}
