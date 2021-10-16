package ui;

import com.formdev.flatlaf.FlatDarkLaf;
import utils.LogHandler;

import javax.swing.*;
import java.awt.*;

public class InterfaceManager {

    private static JFrame frame;
    private static int initStage = 0;
    private static InterfaceManager manager;
    private static GameLinker linker;

    public static GameLinker getLinker() {
        return linker;
    }

    public static void initialize() {
        manager = new InterfaceManager();
        FlatDarkLaf.setup();
        manager.showFirst();
    }

    private void showFirst() {
        if(initStage != 0) {
            LogHandler.getLogger().warning("Tried to initialize InterfaceManager when it was already initialized.");
            return;
        }
        JFrame frame = new JFrame("Forbidden Island Project.");
        BorderLayout layout = new BorderLayout();
        JLabel label = new JLabel("This is a temporary label... We'll fix it later.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JButton button = new JButton("Exit");
        button.addActionListener (e -> {
            frame.setVisible(false);
            LogHandler.getLogger().info("Frame has been exited.");
        });
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(button, BorderLayout.SOUTH);
        panel.add(label, BorderLayout.CENTER);
        frame.add(panel);
        frame.setSize(400, 200);
        frame.setVisible(true);
        initStage++;
    }





}
