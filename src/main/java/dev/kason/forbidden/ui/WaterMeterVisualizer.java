/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.board.WaterMeter;
import com.github.swang04.forbidden.ui.Visualizer;
import dev.kason.forbidden.ImageStorage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;

public class WaterMeterVisualizer extends Visualizer<WaterMeter> {

    private final BufferedImage image = ImageStorage.retrieveImage("water_meter");
    private final BufferedImage pointer = ViewManager.getScaledImage(ImageStorage.retrieveImage("water_meter_pointer.png"), 100, 50);

    private JPanel panel;

    @Override
    public JComponent visualize(WaterMeter object) {
        int state = object.getState();
        panel = new JPanel();
        JPanel wrapper = new JPanel();
        JLabel label = new JLabel(new ImageIcon(ViewManager.getScaledImage(image, 200, 600)));
        wrapper.add(label);
        wrapper.setBackground(ViewManager.getTransparent());
        panel.add(wrapper);
        panel.setBackground(ViewManager.getTransparent());
        return panel;
    }
}
