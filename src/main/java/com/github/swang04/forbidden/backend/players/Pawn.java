package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.board.Tile;

import java.util.List;

public class Pawn {

    private Tile tile;
    private Player player;

    public Pawn() {
    }

    public Player getControllingPlayer() {
        return player;
    }

    public Tile getTile() {
        return tile;
    }

    public List<Tile> getPossibleTiles() {
        return null;
    }

    public boolean canTradeWith(Pawn pawn) {
        return false;
    }
}
