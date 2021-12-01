/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.players.Player;
import dev.kason.forbidden.ImageStorage;

import java.awt.image.BufferedImage;

public enum Treasure implements InventoryItem {
    EARTH_STONE,
    CRYSTAL_OF_FIRE,
    STATUE_OF_WIND,
    OCEAN_CHALICE;

    private Player holder = null;

    static {
        for (Treasure value : values()) {
            value.image = ImageStorage.retrieveImage(value.getFileName());
        }
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

    @Override
    public Player getHolder() {
        return holder;
    }

    public void setHolder(Player holder) {
        this.holder = holder;
    }
}
