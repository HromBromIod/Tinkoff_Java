package edu.hw7;

import edu.hw7.Task3.ReadWriteLockedPersonDatabase;
import edu.hw7.Task3.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task35Test {
    private static final ReadWriteLockedPersonDatabase PERSON_DATABASE = new ReadWriteLockedPersonDatabase();

    private final Thread thread1 = new Thread(() -> {
        try {
            PERSON_DATABASE.add(new Person(0, "Klaus", "Maine", "12345"));
            PERSON_DATABASE.add(new Person(1, "Rudolf", "Shenker", "54321"));
            PERSON_DATABASE.add(new Person(2, null, "Top", "5"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });

    private final Thread thread2 = new Thread(() -> {
        try {
            PERSON_DATABASE.add(new Person(3, "Matias", "Iabs", "67890"));
            PERSON_DATABASE.add(new Person(4, "Mikki", "De", "09876"));
            PERSON_DATABASE.add(new Person(5, "Scorpions", null, "0"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });

    @BeforeEach
    void setUp() {
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("adding and size test")
    void addingAndSizeTest() {
        assertEquals(6, PERSON_DATABASE.size());
    }

    @Test
    @DisplayName("correct data find by name test")
    void correctDataFindByNameTest() throws InterruptedException {
        assertNotNull(PERSON_DATABASE.findByName("Klaus"));
    }

    @Test
    @DisplayName("incorrect data find by name test")
    void incorrectDataFindByAddressTest() throws InterruptedException {
        assertNull(PERSON_DATABASE.findByAddress("Top"));
    }

    @Test
    @DisplayName("delete test")
    void deleteTest() throws InterruptedException {
        PERSON_DATABASE.delete(0);
        assertEquals(5, PERSON_DATABASE.size());
    }

    @Test
    @DisplayName("incorrect id delete test")
    void incorrectIdDeleteTest() throws InterruptedException {
        PERSON_DATABASE.delete(6);
        assertEquals(6, PERSON_DATABASE.size());
    }

    @Test
    @DisplayName("multi thread delete test")
    void multiThreadDeleteTest() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                PERSON_DATABASE.delete(0);
                PERSON_DATABASE.delete(1);
                PERSON_DATABASE.delete(2);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                PERSON_DATABASE.delete(3);
                PERSON_DATABASE.delete(4);
                PERSON_DATABASE.delete(5);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        assertEquals(0, PERSON_DATABASE.size());
    }
}
