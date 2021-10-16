package game.board;

import utils.LogHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

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

    public String getFormalName() {
        return formalName;
    }

    public BufferedImage getRegularImage() {
        return regularImage;
    }

    public BufferedImage getFloodedImage() {
        return floodedImage;
    }

    @Override
    public String toString() {
        return "Type: " + formalName;
    }
}