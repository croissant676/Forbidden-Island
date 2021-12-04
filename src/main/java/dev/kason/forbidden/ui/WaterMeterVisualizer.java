/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.WaterMeter;
import com.github.swang04.forbidden.ui.Visualizer;
import dev.kason.forbidden.ImageStorage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class WaterMeterVisualizer extends Visualizer<WaterMeter> {

    private final BufferedImage pointer = ViewManager.getScaledImage(ImageStorage.retrieveImage("water_meter_pointer.png"), 100, 50);

    private final JLabel spinner = new JLabel();
    private JLabel label = new JLabel();

    public void updatePanel() {
        WaterMeter waterMeter = Board.getInstance().getWaterMeter();
        label.setIcon(new ImageIcon(ViewManager.getScaledImage(ImageStorage.retrieveImage(waterMeter.getFileLocationForImage()), 150, 450)));
        spinner.setText("# Flooded/Turn:" + waterMeter.getNumberOfCards());
    }

    @Override
    public JComponent visualize(WaterMeter object) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label = new JLabel(new ImageIcon(ViewManager.getScaledImage(ImageStorage.retrieveImage(object.getFileLocationForImage()), 150, 450)));
        panel.add(label);
        panel.setBackground(ViewManager.getTransparent());
        spinner.setText("# Flooded/Turn:" + object.getNumberOfCards());
        spinner.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
        panel.add(spinner);
        return panel;
    }
}
