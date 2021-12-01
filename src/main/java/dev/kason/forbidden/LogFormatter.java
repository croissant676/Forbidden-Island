/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

// Author: Kason
public class LogFormatter extends Formatter {

    private static final LogFormatter singleInstance = new LogFormatter();
    private static final SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss.SSS MM/dd/yyyy");

    public static Formatter getFormatter() {
        return singleInstance;
    }

    private static boolean isAnsiEnabled = checkAnsiEnabled();

    public static boolean checkAnsiEnabled() {
        String str = System.getProperty("log.ansi.enabled");
        if (str == null) {
            isAnsiEnabled = false;
        } else {
            isAnsiEnabled = str.equalsIgnoreCase("true");
        }
        return isAnsiEnabled;
    }

    private static @NotNull String returnAnsi(Level level) {
        if (!isAnsiEnabled) {
            return "";
        } else if (level.equals(Level.INFO)) {
            return "\u001B[34m";
        } else if (level.equals(Level.SEVERE)) {
            return "\u001B[31m";
        } else if (level.equals(Level.WARNING)) {
            return "\u001B[33m";
        } else if (level.equals(Level.CONFIG)) {
            return "\u001B[36m";
        } else {
            return "\u001B[0m";
        }
    }

    @Override
    public String format(@NotNull LogRecord record) {
        StringBuilder builder = new StringBuilder();
        Date date = Date.from(record.getInstant());
        String color = returnAnsi(record.getLevel());
        builder.append(color);
        builder.append("[");
        builder.append(format.format(date));
        builder.append("][");
        builder.append(record.getLevel().getName().toUpperCase());
        builder.append("](");
        builder.append(record.getLoggerName());
        builder.append("): ");
        builder.append(record.getMessage());
        builder.append("\n");
        return builder.toString();
    }


}
