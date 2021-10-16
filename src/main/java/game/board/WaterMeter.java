package game.board;

import game.Visualizable;

import javax.swing.*;

public class WaterMeter implements Visualizable {

    private final JProgressBar visual;

    public WaterMeter(int value) {
        this.visual = new JProgressBar(JProgressBar.VERTICAL);
        visual.setMinimum(2);
        visual.setMaximum(6);
        visual.setValue(value);
    }

    public int getValue() {
        return visual.getValue();
    }

    public void setValue(int value) {
        visual.setValue(value);
    }

    @Override
    public JProgressBar getVisual() {
        return visual;
    }
}
