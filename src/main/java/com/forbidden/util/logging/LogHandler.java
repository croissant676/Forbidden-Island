package com.forbidden.util.logging;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

// Author: Kason
public final class LogHandler extends Handler {

    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS dd/MM/yyyy");
    private final PrintStream stream;
    private final boolean ansi;

    public LogHandler(OutputStream stream) {
        this(stream, true);
    }

    public LogHandler(OutputStream stream, boolean ansi) {
        this.ansi = ansi;
        if (stream instanceof PrintStream printStream) {
            this.stream = printStream;
        } else {
            this.stream = new PrintStream(stream);
        }
    }

    private static String getAnsiFor(Level level) {
        if (level.equals(Level.SEVERE)) {
            return "\u001B[31m";
        } else if (level.equals(Level.WARNING)) {
            return "\u001B[33m";
        } else if (level.equals(Level.CONFIG)) {
            return "\u001B[36m";
        } else {
            return "";
        }
    }

    @Override
    public void publish(LogRecord record) {
        StringBuilder builder = new StringBuilder();
        if(ansi) builder.append(getAnsiFor(record.getLevel()));
        builder.append("[");
        Date time = Date.from(record.getInstant());
        builder.append(format.format(time));
        builder.append("][");
        builder.append(record.getLevel().getName());
        builder.append("](");
        builder.append(record.getLoggerName());
        builder.append("): ");
        builder.append(record.getMessage());
        stream.println(builder);
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }

    @Override
    public String toString() {
        return "LogHandler{" +
                "stream=" + stream +
                ", ansi=" + ansi +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogHandler that)) return false;
        return ansi == that.ansi && Objects.equals(stream, that.stream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stream, ansi);
    }
}
