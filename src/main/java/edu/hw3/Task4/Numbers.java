package edu.hw3.Task4;

public enum Numbers {
    ZERO(0), ONE(1), FOUR(4), FIVE(5), SIX(6), NINE(9), TEN(10), HUNDRED(100),
    THOUSAND(1000);
    private final Integer value;

    Numbers(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return value;
    }
}
