package edu.hw3.Task6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task6 {
    private Task6() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        ThisMarket market = new ThisMarket();
        market.add(new Stock(1));
        market.add(new Stock(2));
        market.add(new Stock(0));
        market.remove(market.mostValuableStock());
        market.add(new Stock(1));
        market.remove(market.mostValuableStock());
        LOGGER.info(market.mostValuableStock().getPrice());
    }
}