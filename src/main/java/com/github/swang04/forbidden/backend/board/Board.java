/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.board;

import com.github.swang04.forbidden.backend.treasure.FloodDeck;
import dev.kason.forbidden.logging.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class Board {

    private static Board instance;
    private final Tile[][] tiles;
    private Map<TileType, Tile> tileTypeTileMap = null;

    private FloodDeck floodDeck;

    private static final Logger logger = Log.logger();

    private Board(int seed) {
        logger.info("Created board!");
        instance = this;
        tiles = new Tile[6][6];
        List<TileType> tileTypeList = new ArrayList<>(List.of(TileType.values()));
        Collections.shuffle(tileTypeList, new Random(seed));
        int count = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                // Creates the desired shape
                if (row + col < 2 || row + col > 8 || Math.abs(row - col) >= 4) continue;
                tiles[row][col] = new Tile(row, col, tileTypeList.get(count++));
            }
        }
    }

    public static Board getInstance() {
        return instance;
    }

    public static Board create() {
        return create(0);
    }

    public static Board create(int seed) {
        return new Board(seed);
    }

    public Tile getTileAt(int x, int y) {
        if (isValidTile(x, y)) {
            return tiles[x][y];
        }
        return null;
    }

    public FloodDeck getFloodDeck() {
        return floodDeck;
    }

    private void initTileTypeTileMap() {
        logger.info("Initializing TileTypeTileMap");
        tileTypeTileMap = new HashMap<>();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Tile tile = tiles[row][col];
                if (tile != null) {
                    tileTypeTileMap.put(tile.getTileType(), tile);
                }
            }
        }
    }

    public Tile getTileFor(TileType tileType) {
        if (tileTypeTileMap == null) {
            initTileTypeTileMap();
        }
        return tileTypeTileMap.get(tileType);
    }

    public boolean canMoveTo(int x, int y) {
        return isNotNull(x, y) && getTileAt(x, y).getTileState() != TileState.SUNK;
    }

    public boolean isNotNull(int x, int y) {
        return isValidTile(x, y) && getTileAt(x, y) != null;
    }

    public boolean isValidTile(int x, int y) {
        return x >= 0 && x < 6 && y >= 0 && y < 6;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Tile tile = getTileAt(row, col);
                if (tile == null) {
                    builder.append(" ".repeat(20));
                } else {
                    String str = tile.shortRep();
                    // Use string.format possibly?
                    builder.append(str).append(" ".repeat(20 - str.length()));
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
