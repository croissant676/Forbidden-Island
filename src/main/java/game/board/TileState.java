package game.board;

public enum TileState {
    DRY,
    FLOODED,
    SUNK;

    public String getFormalName() {
        char firstChar = name().charAt(0);
        return Character.toUpperCase(firstChar) + name().substring(1);
    }

    @Override
    public String toString() {
        return "State:" + name();
    }



}
