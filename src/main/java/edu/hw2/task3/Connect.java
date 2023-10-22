package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connect {
    public static final String CONNECTED = "Server has connected.";
    public static final String CONNECTION_ERROR = "Error! Connection is unavailable.";
    public static final String CLOSED = "Connection has closed.";

    public final static Logger LOGGER = LogManager.getLogger();

    Connect() {
    }

    public interface Connection extends AutoCloseable {
        void execute(String command);

        void close();
    }

    public static class ConnectionException extends RuntimeException {
        public ConnectionException(String errorMessage, Throwable cause) {
            super(errorMessage, cause);
        }
    }

    public static class StableConnection implements Connection {
        public void execute(String command) {
            LOGGER.info(CONNECTED);
        }

        public void close() {
            LOGGER.info(CLOSED);
        }
    }

    public static class FaultyConnection implements Connection {
        public void execute(String command) throws ConnectionException {
            final int intCase = 3;
            Random random = new Random();
            if (random.nextInt(intCase) == 2) {
                throw new ConnectionException(CONNECTION_ERROR, new RuntimeException());
            } else {
                LOGGER.info(CONNECTED);
            }
        }

        public void close() {
            LOGGER.info(CLOSED);
        }
    }
}
