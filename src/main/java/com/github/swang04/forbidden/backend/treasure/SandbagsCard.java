/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.board.TileState;
import com.github.swang04.forbidden.backend.players.Player;

public class SandbagsCard implements TreasureDeckCard {

    private Player player;
    private boolean used = false;

    @Override
    public Player getHolder() {
        return player;
    }

    @Override
    public void setHolder(Player player) {
        this.player = player;
    }

    @Override
    public void onDraw(Player player) {
        player.receiveCard(this);
    }

    public void applyAction(Tile tile) {
        if (used) return;
        used = true;
        if (tile.getTileState() == TileState.SUNK) {
            tile.setTileState(TileState.FLOODED);
        } else {
            tile.setTileState(TileState.DRY);
        }
    }

    @Override
    public String toString() {
        return "Sandbags";
    }
}
