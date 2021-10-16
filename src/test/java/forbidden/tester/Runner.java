package forbidden.tester;

import forbidden.utils.LogHandler;

import java.util.logging.Logger;

public class Runner {
    public static void main(String[] args) {
        System.setProperty(LogHandler.getConfigContext(), "true");
        Logger logger = Logger.getLogger(LogHandler.getId());
        logger.info("Hello World!");
        logger.warning("There are dangers ahead.");
        logger.severe("This is dangerous.");
        logger.config("You have passed the danger.");
    }
}
