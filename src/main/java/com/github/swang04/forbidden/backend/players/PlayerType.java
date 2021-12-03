/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.board.TileType;
import dev.kason.forbidden.ImageStorage;

import java.awt.image.BufferedImage;
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

    public String getFileLocation() {
        return "icon_" + name().toLowerCase();
    }
}
