/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.board.TileType;
import dev.kason.forbidden.ImageStorage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public enum Treasure {
    EARTH_STONE,
    CRYSTAL_OF_FIRE,
    STATUE_OF_WIND,
    OCEAN_CHALICE;

    private final List<TileType> tileTypes = new ArrayList<>();
    private boolean takenYet = false;

    static {
        for (Treasure value : values()) {
            value.image = ImageStorage.retrieveImage(value.getFileName());
        }
    }

    public List<TileType> getTileTypes() {
        return tileTypes;
    }

    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public String getFileName() {
        return switch (this) {
            case EARTH_STONE -> "card_treasure_earth";
            case CRYSTAL_OF_FIRE -> "card_treasure_fire";
            case OCEAN_CHALICE -> "card_treasure_ocean";
            case STATUE_OF_WIND -> "card_treasure_wind";
        } + ".png";
    }

    public boolean isTakenYet() {
        return takenYet;
    }

    public void setTakenYet(boolean takenYet) {
        this.takenYet = takenYet;
    }

    public String getFormalName() {
        return switch (this) {
            case STATUE_OF_WIND -> "Statue of Wind";
            case EARTH_STONE -> "Earth Stone";
            case OCEAN_CHALICE -> "Ocean Chalice";
            case CRYSTAL_OF_FIRE -> "Crystal Of File";
        };
    }

    @Override
    public String toString() {
        return name();
    }
}
