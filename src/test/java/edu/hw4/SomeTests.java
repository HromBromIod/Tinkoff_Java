package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
        Optional<Animal> expected = Optional.of(MyAnimalsLists.myFirstList.get(0));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Heaviest animal smaller then k test Error")
    void task8Test2() {
        Optional<Animal> actual = Tasks.heaviestAnimalFromKTask8(MyAnimalsLists.myFirstList, -2);
        Optional<Animal> expected = Optional.empty();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Summary of paws test")
    void task9Test() {
        int actual = Tasks.summaryOfPawsTask9(MyAnimalsLists.myFirstList);
        int expected = 24;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("List of (age != paws) animals test")
    void task10Test() {
        List<Animal> actual = Tasks.listOfAnimalsAgeIsNotEqualsPawsTask10(MyAnimalsLists.mySecondList);
        List<Animal> expected = new ArrayList<>();
        for (Animal o : MyAnimalsLists.mySecondList) {
            if (!o.equals(MyAnimalsLists.mySecondList.get(0))) {
                expected.add(o);
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("List of animals who bites and (height > 100) test")
    void task11Test() {
        List<Animal> actual = Tasks.listOfAnimalsWhoBitesAndHeightMoreHundredTask11(MyAnimalsLists.mySecondList);
        List<Animal> expected = new ArrayList<>();
        expected.add(MyAnimalsLists.mySecondList.get(1));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Count of (weight > height) animals test")
    void task12Test() {
        int actual = Tasks.countOfAnimalsWeightMoreHeightTask12(MyAnimalsLists.mySecondList);
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("List of animals who's names is two words test")
    void task13Test() {
        List<Animal> actual = Tasks.listOfAnimalsNamesMoreTwoWordsTask13(MyAnimalsLists.myFirstList);
        List<Animal> expected = new ArrayList<>();
        expected.add(MyAnimalsLists.myFirstList.get(2));
        expected.add(MyAnimalsLists.myFirstList.get(5));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("If is (height > k) DOG in list test")
    void task14Test1() {
        Boolean actual = Tasks.ifDogHeightMoreKTask14(MyAnimalsLists.myFirstList, 50);
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("If is (height > k) DOG in list test Error")
    void task14Test2() {
        Boolean actual = Tasks.ifDogHeightMoreKTask14(MyAnimalsLists.myFirstList, -1);
        assertNull(actual);
    }

    @Test
    @DisplayName("Summary weight (k < age < i) animals to animal types test")
    void task15Test1() {
        Map<Animal.Type, Integer> actual = Tasks.summaryWeightTask15(MyAnimalsLists.mySecondList, 5, 7);
        Map<Animal.Type, Integer> expected = new HashMap<>();
        expected.put(Animal.Type.DOG, 6708);
        expected.put(Animal.Type.CAT, 0);
        expected.put(Animal.Type.BIRD, 1230 + 785);
        expected.put(Animal.Type.FISH, 0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Summary weight (k < age < i) animals to animal types test Error1")
    void task15Test2() {
        Map<Animal.Type, Integer> actual = Tasks.summaryWeightTask15(MyAnimalsLists.myFirstList, -1, 100);
        assertNull(actual);
    }

    @Test
    @DisplayName("Summary weight (k < age < i) animals to animal types test Error2")
    void task15Test3() {
        Map<Animal.Type, Integer> actual = Tasks.summaryWeightTask15(MyAnimalsLists.myFirstList, 10, 5);
        assertNull(actual);
    }

    @Test
    @DisplayName("Sorted list of animals test")
    void task16Test() {
        List<Animal> actual = Tasks.sortedListOfAnimalsTask16(MyAnimalsLists.myFirstList);
        List<Animal> expected = new ArrayList<>();
        expected.add(MyAnimalsLists.myFirstList.get(0));
        expected.add(MyAnimalsLists.myFirstList.get(1));
        expected.add(MyAnimalsLists.myFirstList.get(7));
        expected.add(MyAnimalsLists.myFirstList.get(4));
        expected.add(MyAnimalsLists.myFirstList.get(2));
        expected.add(MyAnimalsLists.myFirstList.get(6));
        expected.add(MyAnimalsLists.myFirstList.get(3));
        expected.add(MyAnimalsLists.myFirstList.get(5));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("If SPIDERs bites more then DOGs test")
    void task17Test() {
        Boolean actual = Tasks.ifSpidersBitesMoreDogsTask17(MyAnimalsLists.myFirstList);
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Heaviest FISH from lists test")
    void task18Test() {
        Animal actual = Tasks.heaviestFishFromAllListsTask18(MyAnimalsLists.listOfLists);
        Animal expected = MyAnimalsLists.myFirstList.get(6);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check errors in animals test")
    void task19Test() {
        Map<String, Set<ValidationError>> actual = Tasks.errorsInAnimalsTask19(MyAnimalsLists.myBadList);
        Map<String, Set<ValidationError>> expected = new HashMap<>();
        Set<ValidationError> setErrors = new HashSet<>();

        setErrors.add(new ValidationError("EMPTY name"));
        expected.put("", setErrors);
        setErrors.clear();

        setErrors.add(new ValidationError("NULL type"));
        expected.put("Dog", new HashSet<>());
        setErrors.clear();

        setErrors.add(new ValidationError("NULL sex"));
        expected.put("Чиж CO", setErrors);
        setErrors.clear();

        setErrors.add(new ValidationError("NEGATIVE age"));
        expected.put("Harry", setErrors);
        setErrors.clear();

        setErrors.add(new ValidationError("NEGATIVE height"));
        expected.put("Douglas", setErrors);
        setErrors.clear();

        setErrors.add(new ValidationError("EMPTY name"));
        expected.put("Locker", setErrors);
        setErrors.clear();

        setErrors.add(new ValidationError("NEGATIVE weight"));
        expected.put(null, setErrors);
        setErrors.clear();

        assertEquals(expected, actual);
    }
}
