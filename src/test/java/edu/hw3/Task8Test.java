package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    private Task8Test() {
    }

    @Test
    @DisplayName("Integer collection")
    void backwardIteratorTest1() {
        List<Integer> list = List.of(1, 2, 3);
        BackwardIterator<Integer> iterator = new BackwardIterator<>(list);
        int i = list.size() - 1;
        boolean actual = true;
        while (iterator.hasNext()) {
            if (!Objects.equals(list.get(i), iterator.next())) {
                actual = false;
                break;
            }
            i--;
        }
        assertTrue(actual);
    }

    @Test
    @DisplayName("String collection")
    void backwardIteratorTest2() {
        List<String> list = List.of("1", "2", "3");
        BackwardIterator<String> iterator = new BackwardIterator<>(list);
        int i = list.size() - 1;
        boolean actual = true;
        while (iterator.hasNext()) {
            if (!Objects.equals(list.get(i), iterator.next())) {
                actual = false;
                break;
            }
            i--;
        }
        assertTrue(actual);
    }

    @Test
    @DisplayName("Empty test")
    void backwardIteratorTest3() {
        List<Integer> list = List.of();
        BackwardIterator<Integer> iterator = new BackwardIterator<>(list);
        boolean actual = iterator.hasNext();
        assertFalse(actual);
    }
}
