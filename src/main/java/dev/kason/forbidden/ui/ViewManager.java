/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.formdev.flatlaf.FlatDarkLaf;
import dev.kason.forbidden.Log;
import org.jetbrains.annotations.NotNull;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.logging.Logger;

public class ViewManager {

    private static final HashMap<String, View> viewHashMap = new HashMap<>();
    private static final HashMap<View, JFrame> frameMap = new HashMap<>();
    private static final Logger logger = Log.logger();
    private static final JFrame mainFrame;
    private static View currentView = null;

    static {
        FlatDarkLaf.setup();
        mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void register(@NotNull View view) {
        String str = view.getName();
        if (viewHashMap.containsKey(str)) {
            logger.warning("Conflicting view for " + view.getName());
        }
        viewHashMap.put(str, view);
        String property = System.getProperty("ui.init");
        if (view.getName().equals(property)) {
            display(view);
        }
    }

    private static BoardUI boardUI;

    public static BufferedImage getScaledImage(Image srcImg, int w, int h) {
        // Src: Stack overflow
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    public static BoardUI getBoardUI() {
        return boardUI;
    }

    public static void setBoardUI(BoardUI boardUI) {
        ViewManager.boardUI = boardUI;
    }

    public static void display(String str) {
        display(viewHashMap.get(str));
    }

    public static void display(View view) {
        if (!viewHashMap.containsValue(view)) {
            logger.warning("Displaying something that isn't inside registered yet.. Registering view " + view.getName());
            register(view);
        }
        boolean registerNewFrame = view.isNewFrame();
        if (registerNewFrame) {
            if (frameMap.containsKey(view)) {
                logger.severe("Frame map already contains " + view.getName() + ", should not add again.");
                return;
            }
            JFrame frame = new JFrame(view.getName());
            frameMap.put(view, frame);
            JComponent component = view.getDisplay();
            frame.setSize(component.getSize());
            frame.setVisible(true);
            // Don't want the entire app to close because of an extra frame
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            logger.info("Added frame: " + view.getName());
            return;
        }
        if (currentView != null) {
            mainFrame.removeAll();
            mainFrame.revalidate();
        }
        currentView = view;
        mainFrame.add(currentView.getDisplay());
        mainFrame.setTitle(view.getTitle());
        mainFrame.setSize(view.getDisplay().getSize());
        logger.info("Set the view: " + view.getName());
    }

    public static View getCurrentView() {
        return currentView;
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }
}
