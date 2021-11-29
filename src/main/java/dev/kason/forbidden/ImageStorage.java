/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden;

import dev.kason.forbidden.logging.Log;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ImageStorage {

    Map<String, BufferedImage> images = new HashMap<>();

    public static @Nullable BufferedImage retrieveImage(String str) {
        if (!str.contains(".")) {
            str = str + ".png";
        }
        BufferedImage image = easyRetrieve(str);
        if (image == null) {
            BufferedImage image1 = hardRetrieve(str);
            if (image1 == null) {
                logger.warning("No image for " + str);
                return null;
            }
            images.put(str, image1);
        }
        return image;
    }

    private static BufferedImage hardRetrieve(String str) {
        return null;
    }

}
