/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.board;

import com.github.swang04.forbidden.backend.treasure.Treasure;
import dev.kason.forbidden.logging.Log;

import java.util.Objects;
import java.util.logging.Logger;

public class Tile {

    private static final Logger logger = Log.logger();

    private final byte x;
    private final byte y;
    private final TileType tileType;
    private TileState tileState;
    private final Treasure treasure;

    public Tile(int x, int y, TileType tileType) {
        this.x = (byte) x;
        this.y = (byte) y;
        this.tileType = tileType;
        tileState = TileState.DRY;
        treasure = TileType.getTreasure(tileType);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TileType getTileType() {
        return tileType;
    }

    public TileState getTileState() {
        return tileState;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public void setTileState(TileState tileState) {
        this.tileState = tileState;
    }

    public void progressFlooding() {
        tileState = (tileState == TileState.DRY) ? TileState.FLOODED : TileState.SUNK;
    }

    public Tile getAbove() {
        return getRelation(0, -1);
    }

    public Tile getLeft() {
        return getRelation(-1, 0);
    }

    public Tile getBelow() {
        return getRelation(0, 1);
    }

    public Tile getRight() {
        return getRelation(1, 0);
    }

    public Tile getTopRight() {
        return getRelation(1, -1);
    }

    public Tile getTopLeft() {
        return getRelation(-1, -1);
    }

    public Tile getBottomRight() {
        return getRelation(1, -1);
    }

    public Tile getBottomLeft() {
        return getRelation(-1, 1);
    }

    public Tile getRelation(int xDiff, int yDiff) {
        return Board.getInstance().getTileAt(x + xDiff, y + yDiff);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile tile)) return false;
        return x == tile.x && y == tile.y &&
                Objects.equals(tileType, tile.tileType) && tileState == tile.tileState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, tileType, tileState);
    }

    public String shortRep() {
        return "[" + x + "," + y + ":s=" + tileState.ordinal() + ",t=" + tileType.ordinal() + ",r=" + ((this.treasure == null) ? "n" : treasure.ordinal()) + "]";
    }

    public String managerRep() {
        return "Tr:" + ((this.treasure == null) ? "n" : treasure.ordinal()) + ",TT:" + tileType.ordinal();
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", tileType=" + tileType +
                ", tileState=" + tileState +
                '}';
    }
}
