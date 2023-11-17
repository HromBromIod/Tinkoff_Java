package edu.hw3.Task6;

import java.util.PriorityQueue;

public class ThisMarket implements StockMarket {
    private final PriorityQueue<Stock> stocks = new PriorityQueue<>();

    public void add(Stock stock) {
        stocks.offer(stock);
    }

    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
