package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task1 {
    private static final double FOUR = 4;

    private Task1() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    //main
    public static void main(String[] args) {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(FOUR);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));
        LOGGER.info(res + " = " + res.evaluate());
    }

    public sealed interface Expr {
        double evaluate();

        public record Constant(double newValue) implements Expr {
            public double evaluate() {
                return newValue;
            }
        }

        public record Negate(Expr value) implements Expr {
            public double evaluate() {
                if (value.evaluate() != 0) {
                    return (-1) * value.evaluate();
                } else {
                    return value.evaluate();
                }
            }
        }

        public record Exponent(Expr value, int degree) implements Expr {
            public double evaluate() {
                return Math.pow(value.evaluate(), degree);
            }
        }

        public record Addition(Expr leftValue, Expr rightValue) implements Expr {
            public double evaluate() {
                return leftValue.evaluate() + rightValue.evaluate();
            }
        }

        public record Multiplication(Expr leftValue, Expr rightValue) implements Expr {
            public double evaluate() {
                if (leftValue.evaluate() == 0 || rightValue.evaluate() == 0) {
                    return 0;
                } else {
                    return leftValue.evaluate() * rightValue.evaluate();
                }
            }
        }
    }
}
