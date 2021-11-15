/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.board;

import dev.kason.forbidden.logging.Log;

import java.util.Objects;
import java.util.logging.Logger;

public class Tile {

    private static final Logger logger = Log.logger();

    private final byte x;
    private final byte y;
    private final TileType tileType;
    private TileState tileState;

    public Tile(int x, int y, TileType tileType) {
        this.x = (byte) x;
        this.y = (byte) y;
        this.tileType = tileType;
        tileState = TileState.DRY;
        logger.info("Hello World!");
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

    public void setTileState(TileState tileState) {
        this.tileState = tileState;
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
