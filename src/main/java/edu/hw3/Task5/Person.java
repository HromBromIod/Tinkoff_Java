package edu.hw3.Task5;

import org.jetbrains.annotations.NotNull;

public class Person implements Comparable<Person> {

    private final String firstName;
    private final String secondName;
    private final String sortType;

    public Person(String name, String sortType) {
        if (name.indexOf(' ') != -1) {
            firstName = name.substring(0, name.indexOf(' '));
            secondName = name.substring(name.indexOf(' ') + 1);
        } else {
            firstName = name;
            secondName = "";
        }
        this.sortType = sortType;
    }

    private String getFirstName() {
        return firstName;
    }

    private String getSecondName() {
        return secondName;
    }

    public String getName() {
        if (secondName.isEmpty()) {
            return firstName;
        } else {
            return firstName + " " + secondName;
        }
    }

    @Override
    public int compareTo(@NotNull Person o) {
        int result = 0;
        String name = getSecondName();
        String nameO = o.getSecondName();
        if (name.isEmpty()) {
            name = getFirstName();
        }
        if (nameO.isEmpty()) {
            nameO = o.getFirstName();
        }
        if (sortType.equals("ASC")) {
            result = name.compareTo(nameO);
        }
        if (sortType.equals("DESC")) {
            result = nameO.compareTo(name);
        }
        return result;
    }
}
