package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SomeTests {
    private SomeTests() {
    }

    @Test
    @DisplayName("Height sorting test")
    void task1Test() {
        boolean actual = true;
        List<Animal> task1List = Tasks.heightSortingTask1(MyAnimalsLists.myFirstList);
        for (int i = 0; i < task1List.size() - 1; ++i) {
            if(task1List.get(i).height() > task1List.get(i + 1).height()) {
                actual = false;
                break;
            }
        }
        assertTrue(actual);
    }

    @Test
    @DisplayName("Weight reverse sorting test")
    void task2Test() {
        boolean actual = true;
        List<Animal> task1List = Tasks.weightSortingTask2(MyAnimalsLists.myFirstList, 5);
        for (int i = 0; i < task1List.size() - 1; ++i) {
            if(task1List.get(i).weight() < task1List.get(i + 1).weight()) {
                actual = false;
                break;
            }
        }
        assertTrue(actual);
    }

    @Test
    @DisplayName("Types count test")
    void task3Test() {
        Map<Animal.Type, Long> actual = Tasks.typesOfAnimalsTask3(MyAnimalsLists.myFirstList);
        Map<Animal.Type, Long> expected = new HashMap<>();
        expected.put(Animal.Type.DOG, 2L);
        expected.put(Animal.Type.CAT, 1L);
        expected.put(Animal.Type.SPIDER, 1L);
        expected.put(Animal.Type.FISH, 2L);
        expected.put(Animal.Type.BIRD, 2L);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Longest name test")
    void task4Test() {
        Animal actual = Tasks.longestNameAnimalTask4(MyAnimalsLists.myFirstList);
        Animal expected = MyAnimalsLists.myFirstList.get(5);
        assertEquals(expected, actual);
    }


}
