package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("uncommentedmain")
public class Task5 {
    private Task5() {
    }

    public static Person[] parseContacts(String[] listOfContacts, String sortType) {
        List<Person> newListOfContacts = new ArrayList<>();
        if (listOfContacts == null) {
            return newListOfContacts.toArray(new Person[0]);
        }
        Comparator<Person> comparator = (o1, o2) -> {
            String nameL = o1.getName().trim();
            String nameR = o2.getName().trim();
            if (nameL.indexOf(' ') != -1) {
                nameL = nameL.substring(o1.getName().trim().indexOf(' ') + 1);
            }
            if (o2.getName().trim().indexOf(' ') != -1) {
                nameR = nameR.substring(o2.getName().trim().indexOf(' ') + 1);
            }
            if (o1.getSortType().equals("ASC")) {
                return nameL.compareTo(nameR);
            }
            return nameR.compareTo(nameL);
        };
        for (String o : listOfContacts) {
            String name = o.trim();
            if (!name.isEmpty()) {
                newListOfContacts.add(new Person(name, sortType));
            }
        }
        newListOfContacts.sort(comparator);
        return newListOfContacts.toArray(new Person[0]);
    }
}
