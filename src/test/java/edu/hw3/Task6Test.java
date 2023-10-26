package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.ThisMarket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task6Test {
    private Task6Test() {
    }

    @Test
    @DisplayName("")
    void task6Test1() {
        ThisMarket market = new ThisMarket();
        market.add(new Stock(2437));
        market.add(new Stock(2448));
    }
}
