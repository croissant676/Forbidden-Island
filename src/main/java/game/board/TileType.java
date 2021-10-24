package game.board;

import utils.LogHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Represents a tile type.
 *
 * @author Kason
 * @see Tile
 * @since 1.0.0
 */
public enum TileType {
    /**
     * Represents the Breakers Bridge Tile Type.
     */
    BREAKERS_BRIDGE("Breakers Bridge"),
    /**
     * Represents the Bronze Gate Tile Type.
     */
    BRONZE_GATE("Bronze Gate"),
    /**
     * Represents the Cave of Embers Tile Type.
     */
    CAVE_OF_EMBERS("Cave of Embers"),
    /**
     * Represents the Cave of Shadow Tile Type.
     */
    CAVE_OF_SHADOW("Cave of Shadow"),
    /**
     * Represents the Cliffs of Abandon Tile Type.
     */
    CLIFFS_OF_ABANDON("Cliffs of Abandon"),
    /**
     * Represents the Copper Gate Tile Type.
     */
    COPPER_GATE("Copper Gate"),
    /**
     * Represents the Coral Palace Tile Type.
     */
    CORAL_PALACE("Coral Palace"),
    /**
     * Represents the Crimson Forest Tile Type.
     */
    CRIMSON_FOREST("Crimson Forest"),
    /**
     * Represents the Dunes of Deception Tile Type.
     */
    DUNES_OF_DECEPTION("Dunes of Deception"),
    /**
     * Represents the Gold Gate Tile Type.
     */
    GOLD_GATE("Gold Gate"),
    /**
     * Represents the Howling Garden Tile Type.
     */
    HOWLING_GARDEN("Howling Garden"),
    /**
     * Represents the Iron Gate Tile Type.
     */
    IRON_GATE("Iron Gate"),
    /**
     * Represents the Fool's Landing Tile Type.
     */
    LANDING("Fool's Landing"),
    /**
     * Represents the Lost Lagoon Tile Type.
     */
    LOST_LAGOON("Lost Lagoon"),
    /**
     * Represents the Misty Marsh Tile Type.
     */
    MISTY_MARSH("Misty Marsh"),
    /**
     * Represents the Moon Temple Tile Type.
     */
    MOON_TEMPLE("Temple of the Moon"),
    /**
     * Represents the Breakers Bridge Tile Type.
     */
    OBSERVATORY("Observatory"),
    /**
     * Represents the Observatory Tile Type.
     */
    PHANTOM_ROCK("Phantom Rock"),
    /**
     * Represents the Silver Gate Tile Type.
     */
    SILVER_GATE("Silver Gate"),
    /**
     * Represents the Temple of the Sun Tile Type.
     */
    SUN_TEMPLE("Temple of the Sun"),
    /**
     * Represents the Tidal Palace Tile Type.
     */
    TIDAL_PALACE("Tidal Palace"),
    /**
     * Represents the Twilight Hollow Tile Type.
     */
    TWILIGHT_HOLLOW("Twilight Hollow"),
    /**
     * Represents the Watchtower Tile Type.
     */
    WATCHTOWER("Watchtower"),
    /**
     * Represents the Whispering Garden Tile Type.
     */
    WHISPERING_GARDEN("Whispering Garden");

    private final String formalName;
    private BufferedImage regularImage = null;
    private BufferedImage floodedImage = null;

    TileType(String formalName) {
        this.formalName = formalName;
        String fileName = "/images/" + name().toLowerCase();
        URL dry = getClass().getResource(fileName + "_dry.png");
        URL flooded = getClass().getResource(fileName + "_flooded.png");
        Logger logger = LogHandler.getLogger();
        if (dry == null || flooded == null) {
            if (dry == null && flooded == null) {
                logger.warning("Could not load resources: \"" + fileName + "_dry.png\", \"" + fileName + "_flooded.png\".");
            } else if (flooded != null) {
                logger.warning("Could not load resource: \"" + fileName + "_dry.png\".");
            } else {
                logger.warning("Could not load resource: \"" + fileName + "_flooded.png\".");
            }
        } else {
            try {
                regularImage = ImageIO.read(dry);
                floodedImage = ImageIO.read(flooded);
            } catch (IOException ioException) {
                logger.severe("Attempted to load images:");
                ioException.printStackTrace(LogHandler.getError());
            }
        }
    }

    /**
     * Returns the formal name.
     *
     * @return The formal name.
     * @since 1.0.0
     */
    public String getFormalName() {
        return formalName;
    }

    /**
     * Returns the regular image.
     *
     * @return The regular image.
     * @since 1.0.0
     */
    public BufferedImage getRegularImage() {
        return regularImage;
    }

    /**
     * Returns the flooded image.
     *
     * @return The flooded image.
     * @since 1.0.0
     */
    public BufferedImage getFloodedImage() {
        return floodedImage;
    }

    /**
     * Returns a String representation of this tile type.
     *
     * @return A String representation of this tile type.
     * @since 1.0.0
     */
    @Override
    public String toString() {
        return "Type: " + formalName;
    }
}