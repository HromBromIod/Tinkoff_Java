package edu.hw3.Task6;

import edu.hw3.Task6.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task6 {
    private Task6() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        ThisMarket market = new ThisMarket();
        market.add(new Stock(2437));
        Stock max = new Stock(1337);
        market.add(max);
        market.add(new Stock(2448));
        market.add(new Stock(1337));
        market.remove(market.mostvaluableStock());
        market.add(new Stock(3000));
        max = market.mostvaluableStock();
        LOGGER.info("Hello world!");
    }
}
