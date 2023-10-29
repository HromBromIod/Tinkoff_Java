package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.ThisMarket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {
    private Task6Test() {
    }

    @Test
    @DisplayName("Market test")
    void task6Test() {
        boolean actual = true;
        boolean expected = true;
        Integer[] source = new Integer[] {123, 345, 234};
        ThisMarket market = new ThisMarket();
        for (Integer integer : source) {
            market.add(new Stock(integer));
        }
        Arrays.sort(source);
        for (int i = source.length - 1; i >= 0; --i) {
            if (!source[i].equals(market.mostValuableStock().getPrice())) {
                actual = false;
            }
            market.remove(market.mostValuableStock());
        }
        assertEquals(expected, actual);
    }
}
