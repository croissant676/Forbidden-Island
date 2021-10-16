package game.board;

import utils.LogHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class WaterMeter {

    private final JProgressBar visual;
    private BufferedImage background;

    public WaterMeter(int value) {
        this.visual = new JProgressBar(JProgressBar.VERTICAL);
        visual.setMinimum(2);
        visual.setMaximum(6);
        visual.setValue(value);
        URL resource = WaterMeter.class.getResource("/images/water_meter_background.png");
        if (resource == null) {
            LogHandler.getLogger().warning("Could not load resource: \"/images/water_meter_background.png\".");
        } else {
            try {
                background = ImageIO.read(resource);
            } catch (IOException ioException) {
                LogHandler.getLogger().severe("Attempt to load images:");
                ioException.printStackTrace(LogHandler.getError());
            }
        }
    }

    public BufferedImage getBackground() {
        return background;
    }

    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    public int getValue() {
        return visual.getValue();
    }

    public void setValue(int value) {
        visual.setValue(value);
    }

    public JProgressBar getVisual() {
        return visual;
    }
}
