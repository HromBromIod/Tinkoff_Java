package edu.hw9.Task1;

import java.util.Arrays;

public class Metric {
    private final String name;
    private final double[] data;

    public Metric(String name, double[] data) {
        this.name = name;
        this.data = Arrays.copyOf(data, data.length);
    }

    public String getName() {
        return name;
    }

    public double[] getData() {
        return data;
    }

    public double sum() {
        return Arrays.stream(data).sum();
    }

    public double avg() {
        return Arrays.stream(data).average().orElse(0);
    }

    public double max() {
        return Arrays.stream(data).max().orElse(0);
    }

    public double min() {
        return Arrays.stream(data).min().orElse(0);
    }
}
