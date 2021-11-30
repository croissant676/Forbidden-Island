/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.board;

import com.github.swang04.forbidden.backend.players.PlayerType;
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

    private PlayerType spawn;

    public String getFormalName() {
        return formalName;
    }

    public BufferedImage getRegularImage() {
        return regularImage;
    }

    public BufferedImage getFloodedImage() {
        return floodedImage;
    }

    static {
        LANDING.spawn = PlayerType.PILOT;
        PlayerType.PILOT.setSpawn(LANDING);
        SILVER_GATE.spawn = PlayerType.MESSENGER;
        PlayerType.MESSENGER.setSpawn(SILVER_GATE);
        IRON_GATE.spawn = PlayerType.DIVER;
        PlayerType.DIVER.setSpawn(IRON_GATE);
        COPPER_GATE.spawn = PlayerType.EXPLORER;
        PlayerType.EXPLORER.setSpawn(COPPER_GATE);
        BRONZE_GATE.spawn = PlayerType.ENGINEER;
        PlayerType.ENGINEER.setSpawn(BRONZE_GATE);
        GOLD_GATE.spawn = PlayerType.NAVIGATOR;
        PlayerType.NAVIGATOR.setSpawn(GOLD_GATE);
    }

    TileType(String formalName) {
        this.formalName = formalName;
        spawn = null;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "[" + name() + ",o=" + ordinal() + "]";
    }

    public PlayerType getPlayerTypeSpawn() {
        return spawn;
    }

    public Tile getTile() {
        return Board.getInstance().getTileFor(this);
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