package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.List;
import static edu.hw3.Task5.Person.personComparator;

@SuppressWarnings("uncommentedmain")
public class Task5 {
    private Task5() {
    }

    public static Person[] parseContacts(String[] listOfContacts, String sortType) {
        List<Person> newListOfContacts = new ArrayList<>();
        if (listOfContacts == null) {
            return newListOfContacts.toArray(new Person[0]);
        }
        for (String o : listOfContacts) {
            String name = o.trim();
            if (!name.isEmpty()) {
                newListOfContacts.add(new Person(name));
            }
        }
        newListOfContacts.sort(personComparator(sortType));
        return newListOfContacts.toArray(new Person[0]);
    }
}
