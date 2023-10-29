package edu.hw3.Task6;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private final Integer price;

    public Stock(Integer cost) {
        price = cost;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        return o.getPrice() - this.price;
    }
}
