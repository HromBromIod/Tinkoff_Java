package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        final int FOUR = 4;
        final int EIGHT = 8;
        return switch (type) {
            case CAT, DOG -> FOUR;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> EIGHT;
        };
    }
}
