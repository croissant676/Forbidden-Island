package game.board;

import utils.LogHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class WaterMeter {

    private final JProgressBar visual;
    private static BufferedImage background = null;

    private static WaterMeter meter = null;

    public WaterMeter(int value) {
        if (meter != null) {
            throw new IllegalArgumentException("Water meter is already initialized.");
        }
        this.visual = new JProgressBar(JProgressBar.VERTICAL);
        visual.setMinimum(2);
        visual.setMaximum(6);
        visual.setValue(value);
        meter = this;
        URL resource = WaterMeter.class.getResource("/images/water_meter_background.png");
        try {
            background = ImageIO.read(resource);
        } catch (IOException ioException) {
            LogHandler.getLogger().severe("Attempt to load images:");
            ioException.printStackTrace(LogHandler.getError());
        }
    }


    public void setValue(int value) {
        visual.getValue();
    }

    public JProgressBar getVisual() {
        return visual;
    }
}
