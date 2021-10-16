package game.cards;

import game.board.Tile;
import game.board.TileState;
import utils.LogHandler;

public class FloodCard implements Card{

    private final Tile tile;

    public FloodCard(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    public boolean isUsable() {
        return tile.getState() != TileState.SUBMERGED;
    }

    /** Does not update ui. */
    public Tile floodTile() {
        if(tile.getState() == TileState.DRY) {
            tile.setState(TileState.FLOODED);
        } else if(tile.getState() == TileState.FLOODED) {
            tile.setState(TileState.SUBMERGED);
        } else {
            LogHandler.getLogger().severe("Tried to flood already submerged tile: " + tile);
        }
        return tile;
    }
}
