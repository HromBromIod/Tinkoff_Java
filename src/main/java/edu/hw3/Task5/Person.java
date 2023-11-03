package edu.hw3.Task5;

public class Person {
    private final String name;
    private final String sortType;

    public Person(String name, String sortType) {
        this.name = name;
        this.sortType = sortType;
    }

    public String getName() {
        return name;
    }

    public String getSortType() {
        return sortType;
    }
}
