/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.board.Tile;

import java.util.List;

public class Pawn {

    private Tile tile;
    private final Player player;

    public Pawn(Player player) {
        this.player = player;
    }

    public Player getControllingPlayer() {
        return player;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public List<Tile> getPossibleTiles() {
        return null;
    }

    public boolean canTradeWith(Pawn pawn) {
        return false;
    }
}
