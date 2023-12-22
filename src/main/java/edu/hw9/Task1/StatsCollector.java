package edu.hw9.Task1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class StatsCollector {
    private final BlockingQueue<Metric> collector;

    public StatsCollector(int collectorSize) {
        collector = new LinkedBlockingDeque<>(collectorSize);
    }

    public void put(String metricName, double[] data) throws InterruptedException {
        collector.put(new Metric(metricName, data));
    }

    public String collectStats() throws InterruptedException {
        Metric metric = collector.take();
        return metric.getName() + ": " + metric.sum() + " " + metric.avg() + " " + metric.max() + " " + metric.min();
    }
}
