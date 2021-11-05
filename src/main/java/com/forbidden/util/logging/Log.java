package com.forbidden.util.logging;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

// Author: Kason
public final class Log {

    private static Logger logger;
    private static boolean initialized = false;
    private static final String defaultLoggerName = "c.f.u.l.Log";
    private static LogHandler logHandler = new LogHandler(System.out);

    public static String getDefaultLoggerName() {
        return defaultLoggerName;
    }

    public static LogHandler getLogHandler() {
        return logHandler;
    }

    public static void setLogHandler(LogHandler logHandler) {
        Log.logHandler = logHandler;
    }

    public static @NotNull Logger getLogger(@NotNull Class<?> aClass) {
        checkAndPerformInitialization();
        logger.info("Creating logger for class \"" + aClass.getCanonicalName() + "\"");
        StringBuilder builder = new StringBuilder();
        String[] tokens = aClass.getPackageName().split("\\.");
        for (String token : tokens) {
            builder.append(token.charAt(0)).append('.');
        }
        builder.append(aClass.getSimpleName());
        Logger logger = Logger.getLogger(builder.toString());
        return handle(logger);
    }

    public static @NotNull Logger getLogger() {
        checkAndPerformInitialization();
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if(elements.length < 3) {
            throw new IllegalStateException("Elements size < 3, which is impossible.");
        }
        String className = elements[2].getClassName();
        logger.info("Creating logger for class \"" + className + "\"");
        StringBuilder builder = new StringBuilder();
        String[] tokens = className.split("\\.");
        for (int index = 0; index < tokens.length; index++) {
            if(index == tokens.length - 1) {
                builder.append(tokens[index]);
            } else {
                builder.append(tokens[index].charAt(0));
                builder.append('.');
            }
        }
        Logger logger = Logger.getLogger(builder.toString());
        return handle(logger);
    }

    @Contract("_ -> param1")
    private static @NotNull Logger handle(@NotNull Logger logger) {
        final Handler[] handlers = logger.getHandlers();
        //<editor-fold desc="if(handlers.length > 0) removeAll() else nothing">
        if (handlers.length > 0) {
            final Set<LogHandler> handlerSet = new HashSet<>();
            for (Handler handler : handlers) {
                if (handler instanceof LogHandler logHandler) {
                    handlerSet.add(logHandler);
                } else {
                    logger.removeHandler(handler);
                }
            }
            Handler previous = null;
            for (LogHandler logHandler : handlerSet) {
                if (previous != null) {
                    logger.removeHandler(previous);
                }
                previous = logHandler;
            }
            return logger;
        }
        //</editor-fold>
        logger.addHandler(logHandler);
        return logger;
    }

    private static void checkAndPerformInitialization() {
        if (initialized) return;
        initialized = true;
        LogManager manager = LogManager.getLogManager();
        manager.reset();
        logger = Logger.getLogger(defaultLoggerName);
        handle(logger);
        logger.info("Initialized logging.");
    }

}
