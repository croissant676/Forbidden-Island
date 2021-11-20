/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.board;

import com.github.swang04.forbidden.backend.treasure.Treasure;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

// Author: Kason
public enum TileType {
    BREAKERS_BRIDGE("Breakers Bridge"),
    BRONZE_GATE("Bronze Gate"),
    CAVE_OF_EMBERS("Cave of Embers"),
    CAVE_OF_SHADOW("Cave of Shadow"),
    CLIFFS_OF_ABANDON("Cliffs of Abandon"),
    COPPER_GATE("Copper Gate"),
    CORAL_PALACE("Coral Palace"),
    CRIMSON_FOREST("Crimson Forest"),
    DUNES_OF_DECEPTION("Dunes of Deception"),
    GOLD_GATE("Gold Gate"),
    HOWLING_GARDEN("Howling Garden"),
    IRON_GATE("Iron Gate"),
    LANDING("Fool's Landing"),
    LOST_LAGOON("Lost Lagoon"),
    MISTY_MARSH("Misty Marsh"),
    MOON_TEMPLE("Temple of the Moon"),
    OBSERVATORY("Observatory"),
    PHANTOM_ROCK("Phantom Rock"),
    SILVER_GATE("Silver Gate"),
    SUN_TEMPLE("Temple of the Sun"),
    TIDAL_PALACE("Tidal Palace"),
    TWILIGHT_HOLLOW("Twilight Hollow"),
    WATCHTOWER("Watchtower"),
    WHISPERING_GARDEN("Whispering Garden");

    private final String formalName;
    private BufferedImage regularImage;
    private BufferedImage floodedImage;

    TileType(String formalName) {
        this.formalName = formalName;
    }

    public String getFormalName() {
        return formalName;
    }

    public BufferedImage getRegularImage() {
        return regularImage;
    }

    public BufferedImage getFloodedImage() {
        return floodedImage;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "TileType " + name() + "";
    }

    public static Treasure getTreasure(TileType tileType) {
        return switch (tileType) {
            case CAVE_OF_SHADOW, CAVE_OF_EMBERS -> Treasure.CRYSTAL_OF_FIRE;
            case MOON_TEMPLE, SUN_TEMPLE -> Treasure.EARTH_STONE;
            case TIDAL_PALACE, CORAL_PALACE -> Treasure.OCEAN_CHALICE;
            case WHISPERING_GARDEN, HOWLING_GARDEN -> Treasure.STATUE_OF_WIND;
            default -> null;
        };
    }
}