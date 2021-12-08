/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.board;

import com.github.swang04.forbidden.backend.players.PlayerType;
import com.github.swang04.forbidden.backend.treasure.Treasure;
import dev.kason.forbidden.ImageStorage;
import dev.kason.forbidden.ui.ViewManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.Graphics2D;
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
    private BufferedImage floodCardImage;

    private boolean drawn = false;
    private PlayerType spawn;

    public String getFormalName() {
        return formalName;
    }

    TileType(String formalName) {
        this.formalName = formalName;
        spawn = null;
        loadImage();
    }

    public BufferedImage getFloodCardImage() {
        return floodCardImage;
    }

    public BufferedImage getRegularImage() {
        drawImage();
        return regularImage;
    }

    public BufferedImage getFloodedImage() {
        drawImage();
        return floodedImage;
    }

    public @NotNull String getRegularFileLocation() {
        return "dry_" + name().toLowerCase() + ".png";
    }

    public @NotNull String getFloodFileLocation() {
        return "flooded_" + name().toLowerCase() + ".png";
    }

    public @NotNull String getFloodCardImageFileLocation() {
        return "flood_card_" + name().toLowerCase() + ".png";
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

    private void loadImage() {
        try {
            floodedImage = ViewManager.getScaledImage(ViewManager.getScaledImage(ImageStorage.retrieveImage(getFloodFileLocation()), 100, 100).getSubimage(5, 5, 90, 90), 100, 100);
            regularImage = ViewManager.getScaledImage(ViewManager.getScaledImage(ImageStorage.retrieveImage(getRegularFileLocation()), 100, 100).getSubimage(5, 5, 90, 90), 100, 100);
            floodCardImage = ImageStorage.retrieveImage(getFloodCardImageFileLocation());
        } catch (Exception exception) {
            System.out.println(formalName + " no image :(");
            exception.printStackTrace();
        }
    }

    private void drawImage() {
        if (drawn) return;
        drawn = true;
        if (getTreasure(this) == null) return;
        Graphics2D graphics2D = floodedImage.createGraphics();
        drawImageGivenGraphics(graphics2D);
        graphics2D = regularImage.createGraphics();
        drawImageGivenGraphics(graphics2D);
    }

    private void drawImageGivenGraphics(Graphics2D graphics2D) {
        Treasure treasure = getTreasure(this);
        BufferedImage image = ImageStorage.retrieveImage(treasure.getImageFileName());
        image = ViewManager.getScaledImage(image, 30, 30);
        graphics2D.drawImage(image, 70, 70, null);
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

    public String shortRep() {
        return switch (this) {
            case LANDING -> "FLnd";
            case GOLD_GATE -> "AuGt";
            case IRON_GATE -> "FeGt";
            case SUN_TEMPLE -> "STpl";
            case WATCHTOWER -> "WTow";
            case BRONZE_GATE -> "BzGt";
            case COPPER_GATE -> "CuGt";
            case LOST_LAGOON -> "LLag";
            case MISTY_MARSH -> "MMar";
            case MOON_TEMPLE -> "MTpl";
            case OBSERVATORY -> "Obsv";
            case SILVER_GATE -> "AgGt";
            case CORAL_PALACE -> "CPal";
            case PHANTOM_ROCK -> "PhRk";
            case TIDAL_PALACE -> "TPal";
            case CAVE_OF_EMBERS -> "CvoE";
            case CAVE_OF_SHADOW -> "CvoS";
            case CRIMSON_FOREST -> "CFrt";
            case HOWLING_GARDEN -> "HGdn";
            case BREAKERS_BRIDGE -> "BrkB";
            case TWILIGHT_HOLLOW -> "TwlH";
            case CLIFFS_OF_ABANDON -> "CloA";
            case WHISPERING_GARDEN -> "WGdn";
            case DUNES_OF_DECEPTION -> "DoDc";
        };
    }

}