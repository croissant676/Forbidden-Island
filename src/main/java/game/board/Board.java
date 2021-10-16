package game.board;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static Board board = null;

    public static void initializeAll(long seed) {
        if (board == null) {
            board = new Board(seed);
        }
    }

    private Tile[][] tiles;

    private Board(long seed) {

    }

}

