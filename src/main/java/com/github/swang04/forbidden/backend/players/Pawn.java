/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.board.Tile;

import java.util.Set;

public class Pawn {

    private Tile tile;
    private final Player player;

    private final PlayerType playerType;

    public Pawn(Player player) {
        this.player = player;
        playerType = player.getPlayerType();
        this.tile = playerType.getSpawn().getTile();
    }

    public boolean isOnSameTileAs(Pawn other) {
        return tile.equals(other.getTile());
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

    public Set<Tile> getPossibleTiles() {
        return playerType.getMovements();
    }

    public boolean canTradeWith(Pawn pawn) {
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public String shortRep() {
        return "{" + tile.getX() + "," + tile.getY() + ",t=" + playerType.ordinal() + ",p=" + player.getName() + "}";
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "tile=" + tile +
                '}';
    }
}
