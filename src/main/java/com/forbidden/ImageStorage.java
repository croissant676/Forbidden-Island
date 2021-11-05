package com.forbidden;

import com.forbidden.util.logging.Log;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.logging.Logger;

// Author: Kason
public abstract class ImageStorage {

    protected static Logger logger = Log.getLogger();

    protected static ImageStorage storage;

    public static ImageStorage getStorage() {
        return storage;
    }

    private Map<String, BufferedImage> map;

    abstract public void loadImages();

    public final BufferedImage getImage(String str) {
        return map.get(str);
    }



}