package dev.kason.forbidden.logging;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class Log {

    private static final Formatter formatter = LogFormatter.getFormatter();
    private static final Handler handler = new StreamHandler(System.out, formatter);
    private static final Logger staticLogger = Logger.getLogger("d.k.f.l.Log");
    private static boolean init = false;

    private static void init() {
        if (init) return;
        init = true;
        LogManager manager = LogManager.getLogManager();
        manager.reset();
        handler.setFormatter(formatter);
        staticLogger.setUseParentHandlers(false);
        staticLogger.addHandler(handler);
        staticLogger.setLevel(Level.ALL);
    }

    public static @NotNull Logger logger(String str) {
        init();
        Logger logger = Logger.getLogger(str);
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
        staticLogger.info("Registered new logger: " + str + ".");
        return logger;
    }

    public static @NotNull Logger logger(@NotNull Class<?> aClass) {
        init();
        String name = aClass.getCanonicalName();
        return getLogger(name);
    }

    public static @NotNull Logger logger() {
        init();
        String name = Thread.currentThread().getStackTrace()[2].getClassName();
        return getLogger(name);
    }

    @NotNull
    private static Logger getLogger(String name) {
        String shortened = getFormatted(name);
        Logger logger = Logger.getLogger(shortened);
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
        staticLogger.info("Registered new logger: name = " + shortened + ", from class = " + name + ".");
        return logger;
    }

    private static @NotNull String getFormatted(@NotNull String string) {
        StringBuilder builder = new StringBuilder();
        String[] tokens = string.split("\\.");
        for (int index = 0; index < tokens.length; index++) {
            if (index == tokens.length - 1) {
                builder.append(tokens[index]);
                break;
            }
            builder.append(tokens[index].charAt(0)).append('.');
        }
        return builder.toString();
    }
}
