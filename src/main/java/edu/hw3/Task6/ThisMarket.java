package edu.hw3.Task6;

import org.apache.logging.log4j.core.Filter;
import java.util.PriorityQueue;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ThisMarket implements StockMarket {
    private final PriorityQueue<Stock> que;

    public ThisMarket() {
        que = new PriorityQueue<>();
    }

    public void add(Stock stock) {
        que.offer(stock);
    }

    public void remove(Stock stock) {
        que.remove(stock);
    }

    public Stock mostValuableStock() {
        return que.peek();
    }
}