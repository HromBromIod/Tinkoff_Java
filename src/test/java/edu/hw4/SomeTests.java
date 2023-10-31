package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
            if (task1List.get(i).height() > task1List.get(i + 1).height()) {
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
            if (task1List.get(i).weight() < task1List.get(i + 1).weight()) {
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

    @Test
    @DisplayName("Max sex test1")
    void task5Test1() {
        Animal.Sex actual = Tasks.maxAnimalSexTask5(MyAnimalsLists.myFirstList);
        Animal.Sex expected = Animal.Sex.M;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Max sex test2")
    void task5Test2() {
        Animal.Sex actual = Tasks.maxAnimalSexTask5(MyAnimalsLists.mySecondList);
        Animal.Sex expected = Animal.Sex.F;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Max heights of animal's type test")
    void task6Test() {
        Map<Animal.Type, Animal> actual = Tasks.heaviestAnimalsTask6(MyAnimalsLists.myFirstList);
        Map<Animal.Type, Animal> expected = new HashMap<>();
        expected.put(Animal.Type.DOG, MyAnimalsLists.myFirstList.get(1));
        expected.put(Animal.Type.CAT, MyAnimalsLists.myFirstList.get(0));
        expected.put(Animal.Type.SPIDER, MyAnimalsLists.myFirstList.get(5));
        expected.put(Animal.Type.FISH, MyAnimalsLists.myFirstList.get(6));
        expected.put(Animal.Type.BIRD, MyAnimalsLists.myFirstList.get(2));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Oldest k animal test1")
    void task7Test1() {
        Animal actual = Tasks.oldestAnimalTask7(MyAnimalsLists.myFirstList, 1);
        Animal expected = MyAnimalsLists.myFirstList.get(4);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Oldest k animal test2")
    void task7Test2() {
        Animal actual = Tasks.oldestAnimalTask7(MyAnimalsLists.myFirstList, 3);
        Animal expected = MyAnimalsLists.myFirstList.get(2);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Oldest k animal test Error1")
    void task7Test3() {
        Animal actual = Tasks.oldestAnimalTask7(MyAnimalsLists.myFirstList, -2);
        assertNull(actual);
    }

    @Test
    @DisplayName("Oldest k animal test Error2")
    void task7Test4() {
        Animal actual = Tasks.oldestAnimalTask7(MyAnimalsLists.myFirstList, 10);
        assertNull(actual);
    }

    @Test
    @DisplayName("Heaviest animal smaller then k test")
    void task8Test1() {
        Optional<Animal> actual = Tasks.heaviestAnimalFromKTask8(MyAnimalsLists.myFirstList, 40);
        String expected = MyAnimalsLists.myFirstList.get(5).toString();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Heaviest animal smaller then k test Error")
    void task8Test2() {
        Optional<Animal> actual = Tasks.heaviestAnimalFromKTask8(MyAnimalsLists.myFirstList, -2);
        Optional<Animal> expected = Optional.empty();
        assertEquals(expected, actual);
    }
}
