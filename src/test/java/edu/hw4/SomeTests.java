package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SomeTests {
    private SomeTests() {
    }

    @Test
    @DisplayName("Height sorting test")
    void task1Test() {
        boolean actual = true;
        List<Animal> task1List = Tasks.heightSortingTask1(MyAnimalsLists.MYFIRSTLIST);
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
        List<Animal> task1List = Tasks.weightSortingTask2(MyAnimalsLists.MYFIRSTLIST, 5);
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
        Map<Animal.Type, Long> actual = Tasks.typesOfAnimalsTask3(MyAnimalsLists.MYFIRSTLIST);
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
        Animal actual = Tasks.longestNameAnimalTask4(MyAnimalsLists.MYFIRSTLIST);
        Animal expected = MyAnimalsLists.MYFIRSTLIST.get(5);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Max sex test1")
    void task5Test1() {
        Animal.Sex actual = Tasks.maxAnimalSexTask5(MyAnimalsLists.MYFIRSTLIST);
        Animal.Sex expected = Animal.Sex.M;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Max sex test2")
    void task5Test2() {
        Animal.Sex actual = Tasks.maxAnimalSexTask5(MyAnimalsLists.MYSECONDLIST);
        Animal.Sex expected = Animal.Sex.F;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Max heights of animal's type test")
    void task6Test() {
        Map<Animal.Type, Animal> actual = Tasks.heaviestAnimalsTask6(MyAnimalsLists.MYFIRSTLIST);
        Map<Animal.Type, Animal> expected = new HashMap<>();
        expected.put(Animal.Type.DOG, MyAnimalsLists.MYFIRSTLIST.get(1));
        expected.put(Animal.Type.CAT, MyAnimalsLists.MYFIRSTLIST.get(0));
        expected.put(Animal.Type.SPIDER, MyAnimalsLists.MYFIRSTLIST.get(5));
        expected.put(Animal.Type.FISH, MyAnimalsLists.MYFIRSTLIST.get(6));
        expected.put(Animal.Type.BIRD, MyAnimalsLists.MYFIRSTLIST.get(2));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Oldest k animal test1")
    void task7Test1() {
        Animal actual = Tasks.oldestAnimalTask7(MyAnimalsLists.MYFIRSTLIST, 1);
        Animal expected = MyAnimalsLists.MYFIRSTLIST.get(4);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Oldest k animal test2")
    void task7Test2() {
        Animal actual = Tasks.oldestAnimalTask7(MyAnimalsLists.MYFIRSTLIST, 3);
        Animal expected = MyAnimalsLists.MYFIRSTLIST.get(2);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Oldest k animal test Error1")
    void task7Test3() {
        Animal actual = Tasks.oldestAnimalTask7(MyAnimalsLists.MYFIRSTLIST, -2);
        assertNull(actual);
    }

    @Test
    @DisplayName("Oldest k animal test Error2")
    void task7Test4() {
        Animal actual = Tasks.oldestAnimalTask7(MyAnimalsLists.MYFIRSTLIST, 10);
        assertNull(actual);
    }

    @Test
    @DisplayName("Heaviest animal smaller then k test")
    void task8Test1() {
        Optional<Animal> actual = Tasks.heaviestAnimalFromKTask8(MyAnimalsLists.MYFIRSTLIST, 40);
        Optional<Animal> expected = Optional.of(MyAnimalsLists.MYFIRSTLIST.get(0));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Heaviest animal smaller then k test Error")
    void task8Test2() {
        Optional<Animal> actual = Tasks.heaviestAnimalFromKTask8(MyAnimalsLists.MYFIRSTLIST, -2);
        Optional<Animal> expected = Optional.empty();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Summary of paws test")
    void task9Test() {
        int actual = Tasks.summaryOfPawsTask9(MyAnimalsLists.MYFIRSTLIST);
        int expected = 24;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("List of (age != paws) animals test")
    void task10Test() {
        List<Animal> actual = Tasks.listOfAnimalsAgeIsNotEqualsPawsTask10(MyAnimalsLists.MYSECONDLIST);
        List<Animal> expected = new ArrayList<>();
        for (Animal o : MyAnimalsLists.MYSECONDLIST) {
            if (!o.equals(MyAnimalsLists.MYSECONDLIST.get(0))) {
                expected.add(o);
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("List of animals who bites and (height > 100) test")
    void task11Test() {
        List<Animal> actual = Tasks.listOfAnimalsWhoBitesAndHeightMoreHundredTask11(MyAnimalsLists.MYSECONDLIST);
        List<Animal> expected = new ArrayList<>();
        expected.add(MyAnimalsLists.MYSECONDLIST.get(1));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Count of (weight > height) animals test")
    void task12Test() {
        int actual = Tasks.countOfAnimalsWeightMoreHeightTask12(MyAnimalsLists.MYSECONDLIST);
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("List of animals who's names is two words test")
    void task13Test() {
        List<Animal> actual = Tasks.listOfAnimalsNamesMoreTwoWordsTask13(MyAnimalsLists.MYFIRSTLIST);
        List<Animal> expected = new ArrayList<>();
        expected.add(MyAnimalsLists.MYFIRSTLIST.get(2));
        expected.add(MyAnimalsLists.MYFIRSTLIST.get(5));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("If is (height > k) DOG in list test")
    void task14Test1() {
        Boolean actual = Tasks.ifDogHeightMoreKTask14(MyAnimalsLists.MYFIRSTLIST, 50);
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("If is (height > k) DOG in list test Error")
    void task14Test2() {
        Boolean actual = Tasks.ifDogHeightMoreKTask14(MyAnimalsLists.MYFIRSTLIST, -1);
        assertNull(actual);
    }

    @Test
    @DisplayName("Summary weight (k < age < i) animals to animal types test")
    void task15Test1() {
        Map<Animal.Type, Integer> actual = Tasks.summaryWeightTask15(MyAnimalsLists.MYSECONDLIST, 5, 7);
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
        Map<Animal.Type, Integer> actual = Tasks.summaryWeightTask15(MyAnimalsLists.MYFIRSTLIST, -1, 100);
        assertNull(actual);
    }

    @Test
    @DisplayName("Summary weight (k < age < i) animals to animal types test Error2")
    void task15Test3() {
        Map<Animal.Type, Integer> actual = Tasks.summaryWeightTask15(MyAnimalsLists.MYFIRSTLIST, 10, 5);
        assertNull(actual);
    }

    @Test
    @DisplayName("Sorted list of animals test")
    void task16Test() {
        List<Animal> actual = Tasks.sortedListOfAnimalsTask16(MyAnimalsLists.MYFIRSTLIST);
        List<Animal> expected = new ArrayList<>();
        expected.add(MyAnimalsLists.MYFIRSTLIST.get(0));
        expected.add(MyAnimalsLists.MYFIRSTLIST.get(1));
        expected.add(MyAnimalsLists.MYFIRSTLIST.get(7));
        expected.add(MyAnimalsLists.MYFIRSTLIST.get(4));
        expected.add(MyAnimalsLists.MYFIRSTLIST.get(2));
        expected.add(MyAnimalsLists.MYFIRSTLIST.get(6));
        expected.add(MyAnimalsLists.MYFIRSTLIST.get(3));
        expected.add(MyAnimalsLists.MYFIRSTLIST.get(5));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("If SPIDERs bites more then DOGs test")
    void task17Test() {
        Boolean actual = Tasks.ifSpidersBitesMoreDogsTask17(MyAnimalsLists.MYFIRSTLIST);
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Heaviest FISH from lists test")
    void task18Test() {
        Animal actual = Tasks.heaviestFishFromAllListsTask18(MyAnimalsLists.listOfLists);
        Animal expected = MyAnimalsLists.MYFIRSTLIST.get(6);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check errors in animals with errors test")
    void task19Test1() {
        Map<String, Set<ValidationError>> actual = Tasks.errorsInAnimalsTask19(MyAnimalsLists.MYBADLIST);
        Map<String, Set<ValidationError>> expected = new HashMap<>();
        assertNotEquals(expected, actual);
    }

    @Test
    @DisplayName("Check errors in animals without errors test")
    void task19Test2() {
        Map<String, Set<ValidationError>> actual = Tasks.errorsInAnimalsTask19(MyAnimalsLists.MYFIRSTLIST);
        Map<String, Set<ValidationError>> expected = new HashMap<>();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check errors in animals with errors test")
    void task20Test1() {
        Map<String, String> actual = Tasks.errorsInAnimalsToStringTask20(MyAnimalsLists.MYBADLIST);
        Map<String, String> expected = new HashMap<>();
        expected.put(null, "0: NULL name.");
        expected.put("Harry", "3: NEGATIVE age.");
        expected.put(
            "",
            "1: NULL type, 2: NULL sex, 3: NEGATIVE age, 4: NEGATIVE height, 5: NEGATIVE weight, 6: EMPTY name."
        );
        expected.put("Douglas", "4: NEGATIVE height.");
        expected.put("Locker", "5: NEGATIVE weight.");
        expected.put("Чиж CO", "2: NULL sex.");
        expected.put("Dog", "1: NULL type.");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check errors in animals without errors test")
    void task20Test2() {
        Map<String, String> actual = Tasks.errorsInAnimalsToStringTask20(MyAnimalsLists.MYFIRSTLIST);
        Map<String, String> expected = new HashMap<>();
        assertEquals(expected, actual);
    }
}
