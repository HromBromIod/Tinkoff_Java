package edu.hw3.Task5;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task5 {
    private Task5() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        Person[] list =
            parseContacts(new String[] {"James Hatfield", "Lars Urlich", "   ", " Kirk Hammet", "Robert"}, "ASC");
        for (Person person : list) {
            LOGGER.info(person.getName());
        }
    }

    public static Person[] parseContacts(String[] listOfContacts, String sortType) {
        ArrayList<Person> newListOfContacts = new ArrayList<>();
        String name;
        for (int i = 0; listOfContacts != null && i < listOfContacts.length; ++i) {
            name = listOfContacts[i];
            name = name.trim();
            if (!name.isEmpty()) {
                newListOfContacts.add(new Person(name, sortType));
            }
        }
        if (listOfContacts != null) {
            newListOfContacts.sort(Person::compareTo);
        }
        return newListOfContacts.toArray(new Person[0]);
    }
}
