/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.board.TileState;
import com.github.swang04.forbidden.backend.board.TileType;
import dev.kason.forbidden.ImageStorage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    };

    private BufferedImage image;
    private Pawn pawn;

    public Pawn getPawn() {
        if (pawn == null) {
            Set<Pawn> pawns = Game.getGame().getPawnManager().getPawns();
            for (Pawn pawn1 : pawns) {
                if (pawn1.getPlayerType() == this) return pawn = pawn1;
            }
        }
        return pawn;
    }

    public Player getPlayer() {
        return getPawn().getPlayer();
    }

    public BufferedImage getImage() {
        if (image == null) {
            image = ImageStorage.retrieveImage(getFileLocation());
        }
        return image;
    }

    private TileType spawn;

    public TileType getSpawn() {
        return spawn;
    }

    public void setSpawn(TileType spawn) {
        this.spawn = spawn;
    }

    public String getName() {
        return switch (this) {
            case DIVER -> "Diver";
            case PILOT -> "Pilot";
            case ENGINEER -> "Engineer";
            case EXPLORER -> "Explorer";
            case MESSENGER -> "Messenger";
            case NAVIGATOR -> "Navigator";
        };
    }

    private static final int[] x = {-1, 0, 1, 0};
    private static final int[] y = {0, -1, 0, 1};

    public Color getRepresentingColor() {
        return switch (this) {
            case NAVIGATOR -> Color.YELLOW;
            case MESSENGER -> Color.LIGHT_GRAY;
            case EXPLORER -> Color.GREEN;
            case ENGINEER -> Color.RED;
            case PILOT -> new Color(30, 144, 255);
            case DIVER -> Color.DARK_GRAY;
        };
    }

    public List<Tile> getMovements() {
        List<Tile> tiles = new ArrayList<>();
        for (int index = 0; index < 4; index++) {
            Tile tile = getPawn().getTile().getRelation(x[index], y[index]);
            if (tile != null && tile.getTileState() != TileState.SUNK) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    public String getFileLocation() {
        return "icon_" + name().toLowerCase();
    }
}
