package game.board;

public class Tile {

    private final byte row;
    private final byte col;
    private final TileType tileType;
    private TileState state;

    public Tile(byte row, byte col, TileType tileType) {
        this.row = row;
        this.col = col;
        this.tileType = tileType;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public TileState getState() {
        return state;
    }

    public TileType getTileType() {
        return tileType;
    }


}
