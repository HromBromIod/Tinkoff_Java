package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double newValue) implements Expr {
        public double evaluate() {
            return newValue;
        }
    }

    record Negate(Expr value) implements Expr {
        public double evaluate() {
            if (value.evaluate() != 0) {
                return (-1) * value.evaluate();
            } else {
                return value.evaluate();
            }
        }
    }

    record Exponent(Expr value, int degree) implements Expr {
        public double evaluate() {
            return Math.pow(value.evaluate(), degree);
        }
    }

    record Addition(Expr leftValue, Expr rightValue) implements Expr {
        public double evaluate() {
            return leftValue.evaluate() + rightValue.evaluate();
        }
    }

    record Multiplication(Expr leftValue, Expr rightValue) implements Expr {
        public double evaluate() {
            if (leftValue.evaluate() == 0 || rightValue.evaluate() == 0) {
                return 0;
            } else {
                return leftValue.evaluate() * rightValue.evaluate();
            }
        }
    }
}
