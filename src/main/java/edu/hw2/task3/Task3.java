package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain") public class Task3 {
    private Task3() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    //main
    public static void main(String[] args) throws Connect.ConnectionException {
        PopularCommandExecutor exe = new PopularCommandExecutor();
        exe.updatePackages();
        LOGGER.info("");
        exe.tryExecute("Download file.txt");
        LOGGER.info(0);
    }
}
