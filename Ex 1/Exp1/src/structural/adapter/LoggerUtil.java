package structural.adapter;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public final class LoggerUtil {
    private LoggerUtil() {}

    public static Logger getLogger(String name, String logfile) {
        try {
            File f = new File(logfile).getAbsoluteFile();
            if (f.getParentFile() != null) f.getParentFile().mkdirs();

            Logger logger = Logger.getLogger(name);
            logger.setUseParentHandlers(false);
            for (Handler h : logger.getHandlers()) logger.removeHandler(h);

            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.INFO);
            logger.addHandler(ch);

            FileHandler fh = new FileHandler(f.getPath(), true);
            fh.setLevel(Level.FINE);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);

            logger.setLevel(Level.FINE);
            return logger;
        } catch (IOException e) {
            Logger fallback = Logger.getLogger("fallback");
            fallback.warning("Logging failed: " + e.getMessage());
            return fallback;
        }
    }
}
