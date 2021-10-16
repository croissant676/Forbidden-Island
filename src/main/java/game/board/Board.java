package game.board;

import game.Visualizable;

import javax.swing.*;
import java.util.*;

public class Board implements Visualizable {

    private Tile[][] tiles;

    public Board(long seed) {
        tiles = new Tile[6][6];
        List<TileType> tileTypes = new ArrayList<>(List.of(TileType.values()));
        Collections.shuffle(tileTypes, new Random(seed));
        byte number = 0;
        for (byte row = 0; row < 6; row++) {
            for (byte col = 0; col < 6; col++) {
                byte sum = (byte) (row + col);
                if(sum > 1 && sum < 9 && Math.abs(row - col) < 4) {
                    tiles[row][col] = new Tile(row, col, tileTypes.get(number++));
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Tile tile = tiles[row][col];
                if(tile == null) {
                    builder.append(" ".repeat(15));
                } else {
                    String str = tile.shortened();
                    builder.append(str).append(" ".repeat(15 - str.length()));
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    @Override
    public JComponent getVisual() {
        return null;
    }
}

