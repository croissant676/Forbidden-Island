package utils;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.logging.*;

/**
 *
 *
 *
 *
 * */
public class LogHandler extends Handler {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss.SSS a");
    private static final PrintStream error;
    private static final Logger logger;
    private static final String id = "Forbidden_Island_Game";
    private static final String configContext = "config.show_calling_method";

    static {
        logger = Logger.getLogger(id);
        LogManager.getLogManager().reset();
        logger.setUseParentHandlers(false);
        logger.addHandler(new LogHandler());
        logger.setFilter(null);
        logger.setLevel(Level.ALL);
        error = new PrintStream(new LoggerErrorOutputStream(logger));
    }

    public static String getId() {
        return id;
    }

    public static String getConfigContext() {
        return configContext;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static PrintStream getError() {
        return error;
    }

    public static class LoggerErrorOutputStream extends OutputStream {

        private final Logger logger;

        public LoggerErrorOutputStream(Logger logger) {
            this.logger = logger;
        }

        public Logger getLogger() {
            return logger;
        }

        @Override
        public void write(byte[] bytes) {
            StringBuilder builder = new StringBuilder();
            for (byte aByte : bytes) {
                builder.append((char) aByte);
            }
            logger.severe(builder.toString());
        }

        @Override
        public void write(byte[] bytes, int off, int len) {
            if (off + len > bytes.length || off < 0) {
                return;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < len; i++) {
                builder.append((char) bytes[off + i]);
            }
            logger.severe(builder.toString());
        }

        @Override
        public void write(int character) {
            logger.severe(Character.toString(character));
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() {
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LoggerErrorOutputStream that = (LoggerErrorOutputStream) o;
            return Objects.equals(logger, that.logger);
        }

        @Override
        public int hashCode() {
            return Objects.hash(logger);
        }

        @Override
        public String toString() {
            return "LoggerOutputStream";
        }
    }

    private boolean isClosed = false;

    @Override
    public void publish(LogRecord record) {
        if (isClosed) {
            publish(new LogRecord(Level.WARNING, "Tried to use closed LogHandler. " +
                    "This isn't an issue, because the close method does nothing."));
        }
        StringBuilder builder = new StringBuilder();
        boolean useErr = false;
        Instant instant = record.getInstant();
        LocalDateTime time = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        builder.append('[').append(time.format(formatter)).append(']');
        Level level = record.getLevel();
        if (level.equals(Level.WARNING)) {
            useErr = true;
            builder.append("[WARNING][!]: ");
        } else if (level.equals(Level.SEVERE)) {
            useErr = true;
            builder.append("[ERROR][!!]: ");
        } else if (level.equals(Level.CONFIG)) {
            String str = System.getProperty(configContext);
            if (str == null || !str.equalsIgnoreCase("true")) {
                builder.append("[CONFIG][!]: ");
            } else {
                str = record.getSourceMethodName();
                if (str != null) {
                    builder.append("[CONFIG][!] ~ Method \"").append(str).append("\": ");
                } else {
                    builder.append("[CONFIG][!]: ");
                }
            }
        } else {
            builder.append('[').append(level.getName()).append("]: ");
        }
        if (record.getMessage().contains("\n") || record.getMessage().contains("\r")) {
            String[] strings = record.getMessage().split("\n");
            String prefix = builder.toString();
            for (int index = 0; index < strings.length; index++) {
                builder.append(strings[index]).append('\n');
                if (index != strings.length - 1) {
                    builder.append(prefix);
                }
            }
        } else {
            builder.append(record.getMessage());
        }
        if (record.getThrown() != null) {
            Throwable throwable = record.getThrown();
            builder.append(" :: Exception: ").append(throwable.getClass().getCanonicalName());
            if (throwable.getMessage() != null) {
                builder.append(" caused by ").append(throwable.getMessage());
            }
        }
        if (useErr) {
            System.err.println(builder);
        } else {
            System.out.println(builder);
        }
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
        isClosed = true;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogHandler that = (LogHandler) o;
        return isClosed == that.isClosed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isClosed);
    }

    @Override
    public String toString() {
        return "LogHandler{" +
                "isClosed=" + isClosed +
                '}';
    }
}
