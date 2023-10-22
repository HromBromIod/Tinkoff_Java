package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PopularCommandExecutor {
    private static final int MAX_ATTEMPTS = 3;
    private static final String COMPLETED = " has completed.";
    private final static Logger LOGGER = LogManager.getLogger();
    private final ConnectManager.ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor() {
        final int intCase = 3;
        Random random = new Random();
        if (random.nextInt(intCase) == 2) {
            this.manager = new ConnectManager.DefaultConnectionManager();
        } else {
            manager = new ConnectManager.FaultyConnectionManager();
        }
        this.maxAttempts = MAX_ATTEMPTS;
    }

    public void updatePackages() throws Connect.ConnectionException {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) throws Connect.ConnectionException {
        int countAttempts = 0;
        boolean executeFlag = false;
        try (Connect.Connection connection = manager.getConnection()) {
            Connect.ConnectionException error =
                new Connect.ConnectionException(Connect.CONNECTION_ERROR, new RuntimeException());
            while (countAttempts < maxAttempts && !executeFlag) {
                try {
                    connection.execute(command);
                    executeFlag = true;
                } catch (Connect.ConnectionException exception) {
                    error = new Connect.ConnectionException(exception.getMessage(), exception.getCause());
                }
                countAttempts++;
            }
            if (countAttempts >= maxAttempts) {
                LOGGER.info(error.getMessage() + " Server can't complete " + command + ".");
                throw new Connect.ConnectionException(error.getMessage(), error.getCause());
            } else {
                LOGGER.info(command + COMPLETED);
            }
        } catch (Connect.ConnectionException error) {
            throw new Connect.ConnectionException(error.getMessage(), error.getCause());
        }
    }
}
