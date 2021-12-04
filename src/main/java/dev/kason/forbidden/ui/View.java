/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import javax.swing.JComponent;
import java.util.Objects;

public abstract class View {

    private String title;
    private String name;
    private boolean newFrame;

    public View(String title) {
        this(title, title, false);
    }

    public View(String title, String name) {
        this(title, name, false);
    }

    public View(String title, boolean newFrame) {
        this(title, title, newFrame);
    }

    public View(String title, String name, boolean newFrame) {
        this.title = "Forbidden Island > " + title;
        this.name = name;
        this.newFrame = newFrame;
        ViewManager.register(this);
    }

    public abstract JComponent getDisplay();

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public boolean isNewFrame() {
        return newFrame;
    }

    protected void setNewFrame(boolean newFrame) {
        this.newFrame = newFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof View view)) return false;
        return newFrame == view.newFrame &&
                Objects.equals(title, view.title) &&
                Objects.equals(name, view.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, name, newFrame);
    }

    @Override
    public String toString() {
        return "View{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", newFrame=" + newFrame +
                '}';
    }
}
