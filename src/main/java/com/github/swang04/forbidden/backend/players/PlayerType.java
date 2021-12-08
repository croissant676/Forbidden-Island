/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.board.TileState;
import com.github.swang04.forbidden.backend.board.TileType;
import dev.kason.forbidden.ImageStorage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public enum PlayerType {
    EXPLORER {
        @Override
        public Set<Tile> getMovements() {
            Set<Tile> tiles = super.getMovements();
            Tile pawnTile = getPawn().getTile();
            checkAndAddDiagonals(tiles, pawnTile);
            return tiles;
        }
    },
    DIVER {

        @Override
        public Set<Tile> getMovements() {
            // Simple Flood fill algorithm
            Pawn pawn = getPawn();
            Queue<Tile> tiles = new LinkedList<>();
            Set<Tile> beenTo = new HashSet<>();
            Tile start = pawn.getTile();
            tiles.add(start.getAbove());
            tiles.add(start.getBelow());
            tiles.add(start.getRight());
            tiles.add(start.getLeft());
            while(!tiles.isEmpty()){
                Tile tile = tiles.remove();
                if(tile == null || tile.getTileState() == TileState.DRY || beenTo.contains(tile)) {
                    continue;
                }
                beenTo.add(tile);
                tiles.add(tile.getAbove());
                tiles.add(tile.getBelow());
                tiles.add(tile.getRight());
                tiles.add(tile.getLeft());
            }
            Set<Tile> def = super.getMovements();
            for (Tile pawnTile : beenTo) {
                def.add(pawnTile);
                Tile tile = pawnTile.getLeft();
                if (tile != null && tile.getTileState() != TileState.SUNK) def.add(tile);
                tile = pawnTile.getRight();
                if (tile != null && tile.getTileState() != TileState.SUNK) def.add(tile);
                tile = pawnTile.getBelow();
                if (tile != null && tile.getTileState() != TileState.SUNK) def.add(tile);
                tile = pawnTile.getAbove();
                if (tile != null && tile.getTileState() != TileState.SUNK) def.add(tile);
            }
            return def;
        }
        @Override
        public Set<Tile> getShoreUp() {
            return getDefaultShoreUp();
        }

        @Override
        public boolean shouldDiffSUAndMovements() {
            return true;
        }
    },
    PILOT {
        @Override
        public Set<Tile> getMovements() {
            Set<Tile> tiles = new HashSet<>();
            Board instance = Board.getInstance();
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 6; col++) {
                    if (instance.isNotNull(row, col) && instance.getTileAt(row, col).getTileState() != TileState.SUNK) {
                        tiles.add(instance.getTileAt(row, col));
                    }
                }
            }
            return tiles;
        }

        @Override
        public Set<Tile> getShoreUp() {
            return getDefaultShoreUp();
        }

        @Override
        public boolean shouldDiffSUAndMovements() {
            return true;
        }
    },
    ENGINEER { // Code is built in
    },
    MESSENGER { // Code is built in
    },
    NAVIGATOR { // Code is built in
    };

    private static void checkAndAddDiagonals(Set<Tile> def, Tile pawnTile) {
        Tile tile = pawnTile.getBottomLeft();
        if (tile != null && tile.getTileState() != TileState.SUNK) def.add(tile);
        tile = pawnTile.getBottomRight();
        if (tile != null && tile.getTileState() != TileState.SUNK) def.add(tile);
        tile = pawnTile.getTopLeft();
        if (tile != null && tile.getTileState() != TileState.SUNK) def.add(tile);
        tile = pawnTile.getTopRight();
        if (tile != null && tile.getTileState() != TileState.SUNK) def.add(tile);
    }

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

    public Set<Tile> getMovements() {
        return getDefaultMovements();
    }


    public final Set<Tile> getDefaultMovements() {
        Set<Tile> tiles = new HashSet<>();
        for (int index = 0; index < 4; index++) {
            Tile tile = getPawn().getTile().getRelation(x[index], y[index]);
            if (tile != null && tile.getTileState() != TileState.SUNK) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    public Set<Tile> getShoreUp() {
        Set<Tile> tiles = getMovements();
        tiles.add(pawn.getTile());
        return tiles;
    }

    public final Set<Tile> getDefaultShoreUp() {
        Set<Tile> tiles = getDefaultMovements();
        tiles.add(pawn.getTile());
        return tiles;
    }

    public boolean shouldDiffSUAndMovements() {
        return false;
    }

    public String getFileLocation() {
        return "icon_" + name().toLowerCase();
    }
}
