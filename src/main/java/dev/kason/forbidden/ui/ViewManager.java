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

package dev.kason.forbidden.ui;

import com.formdev.flatlaf.FlatDarkLaf;
import dev.kason.forbidden.logging.Log;
import org.jetbrains.annotations.NotNull;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.util.HashMap;
import java.util.logging.Logger;

public class ViewManager {

    private static final HashMap<String, View> viewHashMap = new HashMap<>();
    private static final HashMap<View, JFrame> frameMap = new HashMap<>();
    private static final Logger logger = Log.logger();
    private static final JFrame mainFrame = new JFrame();
    private static View currentView = null;

    static {
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlatDarkLaf.setup();
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

    public static void display(String str) {
        display(viewHashMap.get(str));
    }

    public static void display(View view) {
        if (!viewHashMap.containsValue(view)) {
            logger.warning("Displaying something that isn't inside registered yet.. Registering");
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
            logger.config("Added frame: " + view.getName());
            return;
        }
        currentView = view;
        mainFrame.add(currentView.getDisplay());
        mainFrame.setTitle(view.getTitle());
        mainFrame.setSize(view.getDisplay().getSize());
        logger.config("Set the view: " + view.getName());
    }

    public static View getCurrentView() {
        return currentView;
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }
}
