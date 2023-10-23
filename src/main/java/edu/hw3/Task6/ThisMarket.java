package edu.hw3.Task6;

import edu.hw3.Task6.Stock;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class ThisMarket implements StockMarket {
    private PriorityQueue<Stock> que;

    public ThisMarket() {
        que = new PriorityQueue<>();
    }

    public void add(Stock stock) {
        que.add(stock);
    }

    public void remove(Stock stock) {
        que.remove(stock);
    }

    public Stock mostvaluableStock() {
        return que.peek();
    }
}
