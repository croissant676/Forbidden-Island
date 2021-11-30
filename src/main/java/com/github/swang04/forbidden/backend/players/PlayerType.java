/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.board.TileType;

import java.util.ArrayList;
import java.util.List;

public enum PlayerType {
    EXPLORER {

    },
    DIVER {

    },
    PILOT {

    },
    ENGINEER {

    },
    MESSENGER {

    },
    NAVIGATOR {
        @Override
        public List<Move> getMoves(Pawn pawn) {
            return super.getMoves(pawn);
        }
    };

    private TileType spawn;

    public TileType getSpawn() {
        return spawn;
    }

    public void setSpawn(TileType spawn) {
        this.spawn = spawn;
    }

    public List<Move> getMoves(Pawn pawn) {
        // TODO: 11/29/2021 Generic movements
        ArrayList<Move> moves = new ArrayList<>();

        return null;
    }

    private void considerMovements(List<Move> moves, Pawn pawn) {
        Tile tile = pawn.getTile();

    }

    public String getFileLocation() {
        return "player_" + name().toLowerCase();
    }
}
