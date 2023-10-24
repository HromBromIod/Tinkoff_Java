package edu.hw3.Task6;

import java.util.PriorityQueue;
import java.util.function.Predicate;

public class ThisMarket implements StockMarket {
    private final PriorityQueue<Stock> que;

    public ThisMarket() {
        que = new PriorityQueue<>();
    }

    public void add(Stock stock) {
        que.offer(stock);
    }

    public void remove(Stock stock) {
        //Predicate<Stock> stockToRemove = st -> st.getPrice().equals(stock.getPrice());
        //que.removeIf(stockToRemove);
        que.remove(stock);
    }

    public Stock mostValuableStock() {
        return que.peek();
    }
}
