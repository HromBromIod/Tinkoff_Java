package edu.hw2;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task3 {
    private static final int MAX_ATTEMPTS = 3;
    private static final String COMPLETED = " has completed.";
    private static final String CONNECTED = "Server has connected.";
    private static final String CLOSED = "Connection has closed.";
    private static final String CONNECTION_ERROR = "Error! Connection is unavailable.";

    private Task3() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    //main
    public static void main(String[] args) throws ConnectionException {
        PopularCommandExecutor exe = new PopularCommandExecutor();
        exe.updatePackages();
        LOGGER.info("");
        exe.tryExecute("Download file.txt");
        LOGGER.info(0);
    }

    public interface AutoCloseable {
        public void close();
    }

    public interface Connection extends AutoCloseable {
        void execute(String command);
    }

    public interface ConnectionManager {
        Connection getConnection();
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

    public static class DefaultConnectionManager implements ConnectionManager {
        public Connection getConnection() {
            final int intCase = 5;
            Random random = new Random();
            if (random.nextInt(intCase) == 2) {
                return new FaultyConnection();
            } else {
                return new StableConnection();
            }
        }
    }

    public static class FaultyConnectionManager implements ConnectionManager {
        public Connection getConnection() {
            return new FaultyConnection();
        }
    }

    public static final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;

        PopularCommandExecutor() {
            final int intCase = 3;
            Random random = new Random();
            if (random.nextInt(intCase) == 2) {
                manager = new DefaultConnectionManager();
            } else {
                manager = new FaultyConnectionManager();
            }
            this.maxAttempts = MAX_ATTEMPTS;
        }

        public void updatePackages() throws ConnectionException {
            tryExecute("apt update && apt upgrade -y");
        }

        void tryExecute(String command) throws ConnectionException {
            final Connection connection = manager.getConnection();
            int countAttempts = 0;
            boolean executeFlag = false;
            ConnectionException error = new ConnectionException(CONNECTION_ERROR, new RuntimeException());
            while (countAttempts < maxAttempts && !executeFlag) {
                try {
                    connection.execute(command);
                    executeFlag = true;
                } catch (ConnectionException exception) {
                    error = new ConnectionException(exception.getMessage(), exception.getCause());
                }
                countAttempts++;
            }
            if (countAttempts >= maxAttempts) {
                throw new ConnectionException(error.getMessage(), error.getCause());
            } else {
                LOGGER.info(command + COMPLETED);
            }
            connection.close();
        }
    }
}
