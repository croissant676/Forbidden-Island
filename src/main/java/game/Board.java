package game;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final Tile[][] tiles;

    public Board(int randomSeed) {
        tiles = new Tile[6][6];

    }

    public Tile getTileAt(int x, int y) {
        return tiles[x][y];
    }

    private static final byte[] HORIZONTAL_CHANGE = {-1, 0, 1, 0};
    private static final byte[] VERTICAL_CHANGE = {0, -1, 0, 1};

    public List<Tile> getAdjacentTiles(Tile tile) {
        return getAdjacentTiles(tile.getRow(), tile.getColumn());
    }

    public List<Tile> getAdjacentTiles(int row, int col) {
        List<Tile> tileList = new ArrayList<>(4);
        for (int index = 0; index < 4; index++) {
            int newRow = row + HORIZONTAL_CHANGE[index];
            int newCol = col + VERTICAL_CHANGE[index];
            if (newRow < 0 || newRow >= 6 ||
                    newCol < 0 || newCol >= 6) {
                tileList.add(tiles[newRow][newCol]);
            }
        }
        return tileList;
    }

    public boolean isValidIndex(int row, int col) {
        return (row < 0 || row >= 6 || col < 0 || col >= 6);
    }

    public Tile[][] getTiles() {
        return tiles;
    }


}

