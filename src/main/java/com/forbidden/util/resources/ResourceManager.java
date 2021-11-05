package com.forbidden.util.resources;

import com.forbidden.util.logging.Log;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class ResourceManager {

    private static final ClassLoader loader = ResourceManager.class.getClassLoader();
    private static final Logger logger = Log.getLogger();

    public static @Nullable URL getResource(String str) {
        return loader.getResource(str);
    }

    public static @NotNull URL getResourceOrError(String str) {
        URL url = loader.getResource(str);
        if (url == null) {
            throw new IllegalArgumentException("No resource for \"" + str + "\"");
        }
        return url;
    }

    public static @Nullable File getFileFor(String resource) {
        URL url = loader.getResource(resource);
        if (url == null) return null;
        try {
            return new File(url.toURI()).getAbsoluteFile();
        } catch (URISyntaxException e) {
            logger.severe(e.getClass().getCanonicalName() + ": " + e.getMessage());
            return null;
        }
    }

    private static final Collection<String> allowedImageExtensions = List.of("jpg", "png", "gif", "jpeg");

    public static @Nullable BufferedImage getImage(String str) {
        File file = getFileFor(str);
        if (file == null) return null;
        String name = file.getName();
        String extension = name.substring(name.lastIndexOf("\\."));
        if(allowedImageExtensions.contains(extension)) {
            logger.severe("Extension \"" + extension + "\" is not a valid extension.");
            return null;
        }
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            logger.severe(e.getClass().getCanonicalName() + ": " + e.getMessage());
            return null;
        }
    }

    private static final File file = initLog();

    private static File initLog() {
        LocalDateTime localDateTime = LocalDateTime.now();
    }

    public static

}
