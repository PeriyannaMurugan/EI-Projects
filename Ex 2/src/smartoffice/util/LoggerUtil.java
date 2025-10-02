package smartoffice.util;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {
    private static Logger logger = Logger.getLogger("SmartOfficeLogger");

    static {
        try {
            logger.setUseParentHandlers(false);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(consoleHandler);

            FileHandler fileHandler = new FileHandler("smartoffice.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

        } catch (IOException e) {
            System.out.println("Logger initialization failed: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
