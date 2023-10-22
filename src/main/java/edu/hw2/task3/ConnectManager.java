package edu.hw2.task3;

import java.util.Random;

public class ConnectManager {
    public interface ConnectionManager {
        Connect.Connection getConnection();
    }

    public static class DefaultConnectionManager implements ConnectionManager {
        public Connect.Connection getConnection() {
            final int intCase = 5;
            Random random = new Random();
            if (random.nextInt(intCase) == 2) {
                return new Connect.FaultyConnection();
            } else {
                return new Connect.StableConnection();
            }
        }
    }

    public static class FaultyConnectionManager implements ConnectionManager {
        public Connect.Connection getConnection() {
            return new Connect.FaultyConnection();
        }
    }
}
