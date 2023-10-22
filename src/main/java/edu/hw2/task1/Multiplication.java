package edu.hw2.task1;

public record Multiplication(Expr leftValue, Expr rightValue) implements Expr {
    public double evaluate() {
        if (leftValue.evaluate() == 0 || rightValue.evaluate() == 0) {
            return 0;
        } else {
            return leftValue.evaluate() * rightValue.evaluate();
        }
    }
}
