package edu.hw2.task1;

public record Addition(Expr leftValue, Expr rightValue) implements Expr {
    public double evaluate() {
        return leftValue.evaluate() + rightValue.evaluate();
    }
}
