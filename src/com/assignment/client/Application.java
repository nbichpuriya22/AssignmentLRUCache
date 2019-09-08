package com.assignment.client;

import com.assignment.engine.ServeData;

import com.assignment.engine.dba.DiscMemory;
import org.apache.log4j.*;


/**
 * Class to call request random data from engine.
 */
public class Application {


    // creates a custom logger and log messages
    private final static Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
        layout.setConversionPattern(conversionPattern);

        // creates console appender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();

        // creates file appender
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("lru_cache.log");
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();

        // configures the root logger
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);

        LOGGER.log(Level.INFO, "Logger configuration done.");

        DiscMemory.showData();

        int tryFor = 10;
        while (tryFor-- > 0) {
            int userId = (int) (Math.random() * 10.0) % 10;
            LOGGER.log(Level.INFO, "Requested data for user id: " + userId);
            String response = ServeData.response(userId);
            LOGGER.log(Level.INFO, "user id: " + userId + " and profile: " + response);
        }
    }
}
