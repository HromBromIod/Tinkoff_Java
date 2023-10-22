package edu.hw2.task1;

public record Constant(double newValue) implements Expr {
    public double evaluate() {
        return newValue;
    }
}
