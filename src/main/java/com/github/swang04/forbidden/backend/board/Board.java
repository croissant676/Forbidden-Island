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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private final Tile[][] tiles;

    public Board(int seed) {
        tiles = new Tile[6][6];
        List<TileType> tileTypeList = new ArrayList<>(List.of(TileType.values()));
        Collections.shuffle(tileTypeList);
        int count = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (row + col < 2 || row + col > 8 || Math.abs(row - col) >= 4) continue;
                tiles[row][col] = new Tile(row, col, tileTypeList.get(count++));
            }
        }
    }

}
