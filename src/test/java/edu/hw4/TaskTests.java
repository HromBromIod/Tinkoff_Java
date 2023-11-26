package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTests {
    private static class AnimalTestLists {
        private AnimalTestLists() {
        }

        public static final List<Animal> FIRST_LIST = Arrays.asList(
            new Animal("мяу1", Animal.Type.CAT, Animal.Sex.M, 6, 32, 5000, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 9, 57, 7001, true),
            new Animal("Чиж CO1", Animal.Type.BIRD, Animal.Sex.F, 7, 46, 1230, false),
            new Animal("Henry1", Animal.Type.FISH, Animal.Sex.F, 2, 5, 120, false),
            new Animal("Douglas1", Animal.Type.BIRD, Animal.Sex.M, 11, 34, 785, true),
            new Animal("Аркадий Павук", Animal.Type.SPIDER, Animal.Sex.M, 3, 15, 220, true),
            new Animal("Locker1", Animal.Type.FISH, Animal.Sex.M, 3, 12, 245, true),
            new Animal("Paper1", Animal.Type.DOG, Animal.Sex.F, 5, 58, 6708, true)
        );
        public static final List<Animal> SECOND_LIST = Arrays.asList(
            new Animal("мяу2", Animal.Type.CAT, Animal.Sex.F, 4, 32, 5000, true),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.F, 9, 101, 7000, true),
            new Animal("Чиж CO2", Animal.Type.BIRD, Animal.Sex.M, 7, 46, 1230, false),
            new Animal("Harry2", Animal.Type.FISH, Animal.Sex.M, 2, 5, 120, false),
            new Animal("Douglas2", Animal.Type.BIRD, Animal.Sex.F, 5, 34, 785, true),
            new Animal("Locker2", Animal.Type.FISH, Animal.Sex.F, 3, 12, 10, true),
            new Animal("Paper2", Animal.Type.DOG, Animal.Sex.M, 5, 90, 6708, true)
        );
        public static final List<Animal> BAD_LIST = Arrays.asList(
            new Animal("", null, null, -1, -1, -1, false),
            new Animal(null, Animal.Type.CAT, Animal.Sex.F, 6, 32, 5000, true),
            new Animal("Dog", null, Animal.Sex.F, 9, 57, 7000, true),
            new Animal("Чиж CO", Animal.Type.BIRD, null, 7, 46, 1230, false),
            new Animal("Harry", Animal.Type.FISH, Animal.Sex.M, -1, 5, 120, false),
            new Animal("Douglas", Animal.Type.BIRD, Animal.Sex.F, 11, -1, 785, true),
            new Animal("Locker", Animal.Type.FISH, Animal.Sex.F, 3, 12, -1, true)
        );
    }

    private TaskTests() {
    }

    @Test
    @DisplayName("Height sorting test")
    void task1Test() {
        boolean actual = true;
        List<Animal> task1List = Tasks.sortAnimalsByHeight(AnimalTestLists.FIRST_LIST);
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
        List<Animal> task1List = Tasks.weightSortingTask2(AnimalTestLists.FIRST_LIST, 5);
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
        Map<Animal.Type, Long> actual = Tasks.typesOfAnimalsTask3(AnimalTestLists.FIRST_LIST);
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
        Animal actual = Tasks.longestNameAnimalTask4(AnimalTestLists.FIRST_LIST);
        Animal expected = AnimalTestLists.FIRST_LIST.get(5);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Max sex test1")
    void task5Test1() {
        Animal.Sex actual = Tasks.maxAnimalSexTask5(AnimalTestLists.FIRST_LIST);
        Animal.Sex expected = Animal.Sex.M;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Max sex test2")
    void task5Test2() {
        Animal.Sex actual = Tasks.maxAnimalSexTask5(AnimalTestLists.SECOND_LIST);
        Animal.Sex expected = Animal.Sex.F;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Max heights of animal's type test")
    void task6Test() {
        Map<Animal.Type, Animal> actual = Tasks.heaviestAnimalsTask6(AnimalTestLists.FIRST_LIST);
        Map<Animal.Type, Animal> expected = new HashMap<>();
        expected.put(Animal.Type.DOG, AnimalTestLists.FIRST_LIST.get(1));
        expected.put(Animal.Type.CAT, AnimalTestLists.FIRST_LIST.get(0));
        expected.put(Animal.Type.SPIDER, AnimalTestLists.FIRST_LIST.get(5));
        expected.put(Animal.Type.FISH, AnimalTestLists.FIRST_LIST.get(6));
        expected.put(Animal.Type.BIRD, AnimalTestLists.FIRST_LIST.get(2));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Oldest k animal test1")
    void task7Test1() {
        Animal actual = Tasks.oldestAnimalTask7(AnimalTestLists.FIRST_LIST, 1);
        Animal expected = AnimalTestLists.FIRST_LIST.get(4);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Oldest k animal test2")
    void task7Test2() {
        Animal actual = Tasks.oldestAnimalTask7(AnimalTestLists.FIRST_LIST, 3);
        Animal expected = AnimalTestLists.FIRST_LIST.get(2);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Oldest k animal test Error1")
    void task7Test3() {
        Animal actual = Tasks.oldestAnimalTask7(AnimalTestLists.FIRST_LIST, -2);
        assertNull(actual);
    }

    @Test
    @DisplayName("Oldest k animal test Error2")
    void task7Test4() {
        Animal actual = Tasks.oldestAnimalTask7(AnimalTestLists.FIRST_LIST, 10);
        assertNull(actual);
    }

    @Test
    @DisplayName("Heaviest animal smaller then k test")
    void task8Test1() {
        Optional<Animal> actual = Tasks.heaviestAnimalFromKTask8(AnimalTestLists.FIRST_LIST, 40);
        Optional<Animal> expected = Optional.of(AnimalTestLists.FIRST_LIST.get(0));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Heaviest animal smaller then k test Error")
    void task8Test2() {
        Optional<Animal> actual = Tasks.heaviestAnimalFromKTask8(AnimalTestLists.FIRST_LIST, -2);
        Optional<Animal> expected = Optional.empty();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Summary of paws test")
    void task9Test() {
        int actual = Tasks.summaryOfPawsTask9(AnimalTestLists.FIRST_LIST);
        int expected = 24;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("List of (age != paws) animals test")
    void task10Test() {
        List<Animal> actual = Tasks.listOfAnimalsAgeIsNotEqualsPawsTask10(AnimalTestLists.SECOND_LIST);
        List<Animal> expected = new ArrayList<>();
        for (Animal o : AnimalTestLists.SECOND_LIST) {
            if (!o.equals(AnimalTestLists.SECOND_LIST.get(0))) {
                expected.add(o);
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("List of animals who bites and (height > 100) test")
    void task11Test() {
        List<Animal> actual = Tasks.listOfAnimalsWhoBitesAndHeightMoreHundredTask11(AnimalTestLists.SECOND_LIST);
        List<Animal> expected = new ArrayList<>();
        expected.add(AnimalTestLists.SECOND_LIST.get(1));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Count of (weight > height) animals test")
    void task12Test() {
        int actual = Tasks.countOfAnimalsWeightMoreHeightTask12(AnimalTestLists.SECOND_LIST);
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("List of animals who's names is two words test")
    void task13Test() {
        List<Animal> actual = Tasks.listOfAnimalsNamesMoreTwoWordsTask13(AnimalTestLists.FIRST_LIST);
        List<Animal> expected = new ArrayList<>();
        expected.add(AnimalTestLists.FIRST_LIST.get(2));
        expected.add(AnimalTestLists.FIRST_LIST.get(5));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("If is (height > k) DOG in list test")
    void task14Test1() {
        Boolean actual = Tasks.ifDogHeightMoreKTask14(AnimalTestLists.FIRST_LIST, 50);
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("If is (height > k) DOG in list test Error")
    void task14Test2() {
        Boolean actual = Tasks.ifDogHeightMoreKTask14(AnimalTestLists.FIRST_LIST, -1);
        assertNull(actual);
    }

    @Test
    @DisplayName("Summary weight (k < age < i) animals to animal types test")
    void task15Test1() {
        Map<Animal.Type, Integer> actual = Tasks.summaryWeightTask15(AnimalTestLists.SECOND_LIST, 5, 7);
        Map<Animal.Type, Integer> expected = new HashMap<>();
        expected.put(Animal.Type.DOG, 6708);
        expected.put(Animal.Type.BIRD, 1230 + 785);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Summary weight (k < age < i) animals to animal types test Error1")
    void task15Test2() {
        Map<Animal.Type, Integer> actual = Tasks.summaryWeightTask15(AnimalTestLists.FIRST_LIST, -1, 100);
        assertNull(actual);
    }

    @Test
    @DisplayName("Summary weight (k < age < i) animals to animal types test Error2")
    void task15Test3() {
        Map<Animal.Type, Integer> actual = Tasks.summaryWeightTask15(AnimalTestLists.FIRST_LIST, 10, 5);
        assertNull(actual);
    }

    @Test
    @DisplayName("Sorted list of animals test")
    void task16Test() {
        List<Animal> actual = Tasks.sortedListOfAnimalsTask16(AnimalTestLists.FIRST_LIST);
        List<Animal> expected = new ArrayList<>();
        expected.add(AnimalTestLists.FIRST_LIST.get(0));
        expected.add(AnimalTestLists.FIRST_LIST.get(1));
        expected.add(AnimalTestLists.FIRST_LIST.get(7));
        expected.add(AnimalTestLists.FIRST_LIST.get(4));
        expected.add(AnimalTestLists.FIRST_LIST.get(2));
        expected.add(AnimalTestLists.FIRST_LIST.get(6));
        expected.add(AnimalTestLists.FIRST_LIST.get(3));
        expected.add(AnimalTestLists.FIRST_LIST.get(5));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("If SPIDERs bites more then DOGs test")
    void task17Test() {
        Boolean actual = Tasks.ifSpidersBitesMoreDogsTask17(AnimalTestLists.FIRST_LIST);
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Heaviest FISH from lists test")
    void task18Test() {
        Animal actual = Tasks.heaviestFishFromAllListsTask18(Arrays.asList(
            AnimalTestLists.FIRST_LIST,
            AnimalTestLists.SECOND_LIST
        ));
        Animal expected = AnimalTestLists.FIRST_LIST.get(6);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check errors in animals with errors test")
    void task19Test1() {
        Map<String, List<AnimalValidator.ValidationError>> actual =
            Tasks.errorsInAnimalsTask19(AnimalTestLists.BAD_LIST);
        Map<String, List<AnimalValidator.ValidationError>> expected = new HashMap<>();
        assertNotEquals(expected, actual);
    }

    @Test
    @DisplayName("Check errors in animals without errors test")
    void task19Test2() {
        Map<String, List<AnimalValidator.ValidationError>> actual =
            Tasks.errorsInAnimalsTask19(AnimalTestLists.FIRST_LIST);
        Map<String, List<AnimalValidator.ValidationError>> expected = new HashMap<>();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check errors in animals with errors test")
    void task20Test1() {
        Map<String, String> actual = Tasks.errorsInAnimalsToStringTask20(AnimalTestLists.BAD_LIST);
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
        Map<String, String> actual = Tasks.errorsInAnimalsToStringTask20(AnimalTestLists.FIRST_LIST);
        Map<String, String> expected = new HashMap<>();
        assertEquals(expected, actual);
    }
}
