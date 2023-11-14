package edu.hw3.Task5;

import java.util.Comparator;

public class Person {
    protected final String firstName;
    protected final String secondName;

    public Person(String name) {
        String personName = name.trim();
        if (personName.indexOf(' ') != -1) {
            firstName = personName.substring(0, personName.indexOf(' '));
            secondName = personName.substring(personName.indexOf(' ') + 1);
        } else {
            firstName = name;
            secondName = "";
        }
    }

    public String getName() {
        if (secondName.isEmpty()) {
            return firstName;
        } else {
            return firstName + " " + secondName;
        }
    }

    public static Comparator<Person> personComparator(String sortType) {
        return (o1, o2) -> {
            String nameL = o1.secondName.isEmpty() ? o1.firstName : o1.secondName;
            String nameR = o2.secondName.isEmpty() ? o2.firstName : o2.secondName;
            if (sortType.equals("ASC")) {
                return nameL.compareTo(nameR);
            }
            if (sortType.equals("DESC")) {
                return nameR.compareTo(nameL);
            }
            throw new IllegalArgumentException("Wrong sort type!");
        };
    }
}
