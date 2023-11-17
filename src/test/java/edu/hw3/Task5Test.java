package edu.hw3;

import edu.hw3.Task5.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task5.Task5.parseContacts;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    private Task5Test() {
    }

    @Test
    @DisplayName("null, \"DESC\" test")
    void task5TestEmpty1() {
        Person[] actual = parseContacts(null, "DESC");
        Person[] expected = new Person[0];
        assertArrayEquals(actual, expected);
    }

    @Test
    @DisplayName("[], \"DESC\" test")
    void task5TestEmpty2() {
        Person[] actual = parseContacts(new String[] {}, "DESC");
        Person[] expected = new Person[0];
        assertArrayEquals(actual, expected);
    }

    @Test
    @DisplayName("Metallica, \"DESC\" test")
    void task5Test1() {
        String[] metallica = new String[] {"James Hatfield", "Lars Urlich", " Kirk Hammet", "Robert", "   "};
        String sortType = "DESC";
        Person[] sorted = parseContacts(metallica, sortType);
        Person[] mySorted = new Person[] {
            new Person(metallica[1].trim()), new Person(metallica[3].trim()),
            new Person(metallica[0].trim()), new Person(metallica[2].trim())};
        boolean actual = true;
        for (int i = 0; i < sorted.length; ++i) {
            if (!sorted[i].getName().equals(mySorted[i].getName())) {
                actual = false;
                break;
            }
        }
        assertTrue(actual);
    }

    @Test
    @DisplayName("Metallica, \"ASC\" test")
    void task5Test2() {
        String[] metallica = new String[] {"James Hatfield", "   ", "Lars Urlich", " Kirk Hammet", "Robert"};
        String sortType = "ASC";
        Person[] sorted = parseContacts(metallica, sortType);
        Person[] mySorted = new Person[] {
            new Person(metallica[3].trim()), new Person(metallica[0].trim()),
            new Person(metallica[4].trim()), new Person(metallica[2].trim())};
        boolean actual = true;
        for (int i = 0; i < sorted.length; ++i) {
            if (!sorted[i].getName().equals(mySorted[i].getName())) {
                actual = false;
                break;
            }
        }
        assertTrue(actual);
    }

    @Test
    @DisplayName("Invalid sort type test")
    void invalidSortTypeTest() {
        String[] metallica = new String[] {"James Hatfield", "   ", "Lars Urlich", " Kirk Hammet", "Robert"};
        String sortType = "ABCDE";
        assertThrows(IllegalArgumentException.class, () -> parseContacts(metallica, sortType));
    }

}
