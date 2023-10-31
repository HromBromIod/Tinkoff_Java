package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Tasks {
    private Tasks() {
    }

    //Ready
    public static List<Animal> heightSortingTask1(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().sorted(Comparator.comparingInt(Animal::height)).collect(Collectors.toList());
    }

    //Ready
    public static List<Animal> weightSortingTask2(List<Animal> listOfAnimals, int k) {
        return listOfAnimals.stream().sorted((o1, o2) -> o2.weight() - o1.weight()).collect(Collectors.toList())
            .subList(0, k);
    }

    //Ready
    public static Map<Animal.Type, Long> typesOfAnimalsTask3(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    //Ready
    public static Animal longestNameAnimalTask4(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().max(Comparator.comparing(o -> o.name().length())).orElse(null);
    }

    //Ready
    public static Animal.Sex maxAnimalSexTask5(List<Animal> listOfAnimals) {
        Map<Animal.Sex, Long> mapAnimalSex =
            listOfAnimals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        if (mapAnimalSex.get(Animal.Sex.F) > mapAnimalSex.get(Animal.Sex.M)) {
            return Animal.Sex.F;
        }
        if (mapAnimalSex.get(Animal.Sex.F) < mapAnimalSex.get(Animal.Sex.M)) {
            return Animal.Sex.M;
        }
        return Animal.Sex.M;
    }

    //Ready
    public static Map<Animal.Type, Animal> heaviestAnimalsTask6(List<Animal> listOfAnimals) {
        Map<Animal.Type, List<Animal>> mapOfAnimals =
            listOfAnimals.stream().collect(Collectors.groupingBy(Animal::type));
        Map<Animal.Type, Animal> mapOfHeaviestAnimal = new HashMap<>();
        for (var o : mapOfAnimals.entrySet()) {
            mapOfHeaviestAnimal.put(
                o.getKey(),
                mapOfAnimals.get(o.getKey()).stream().sorted((o1, o2) -> o2.weight() - o1.weight()).toList().get(0)
            );
        }
        return mapOfHeaviestAnimal;
    }

    //Ready
    public static Animal oldestAnimalTask7(List<Animal> listOfAnimals, int k) {
        return k > 0 && k < listOfAnimals.size() ? listOfAnimals.stream().sorted((o1, o2) -> o2.age() - o1.age()).toList().get(k - 1) : null;
    }

    //Ready
    public static Optional<Animal> heaviestAnimalFromKTask8(List<Animal> listOfAnimals, int k) {
        return k <= 0 ? Optional.empty()
            : listOfAnimals.stream().filter(o -> o.weight() < k).max(Comparator.comparingInt(Animal::weight));
    }

    //Ready
    public static Integer summaryOfPawsTask9(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().mapToInt(Animal::paws).sum();
    }

    //Ready
    public static List<Animal> listOfAnimalsAgeIsNotEqualsPawsTask10(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().filter(o -> o.age() != o.paws()).toList();
    }

    //Ready
    public static List<Animal> listOfAnimalsWhoBitesAndHeightMoreHundredTask11(List<Animal> listOfAnimals) {
        final int HUNDRED = 100;
        return listOfAnimals.stream().filter(o -> o.bites() && o.height() > HUNDRED).toList();
    }

    //Ready
    public static Integer countOfAnimalsWeightMoreHeightTask12(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().filter(o -> o.weight() > o.height()).toList().size();
    }

    //Ready
    public static List<Animal> listOfAnimalsNamesMoreTwoWordsTask13(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().filter(o -> o.name().trim().indexOf(' ') != -1).toList();
    }

    //Ready
    public static Boolean ifDogHeightMoreKTask14(List<Animal> listOfAnimals, int k) {
        return !listOfAnimals.stream().filter(o -> o.type().equals(Animal.Type.DOG) && o.height() > k).toList()
            .isEmpty();
    }

    //Ready
    public static Integer summaryWeightTask15(List<Animal> listOfAnimals, int k, int i) {
        return listOfAnimals.stream().filter(o -> o.age() > k && o.age() < i).toList().stream().mapToInt(Animal::weight)
            .sum();
    }

    //Ready
    public static List<Animal> sortedListOfAnimalsTask16(List<Animal> listOfAnimals) {
        return listOfAnimals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name)).toList();
    }

    //Ready
    public static Boolean ifSpidersBitesMoreDogsTask17(List<Animal> listOfAnimals) {
        Map<Animal.Type, List<Animal>> mapOfSpidersAndDogs =
            listOfAnimals.stream().filter(o -> o.type().equals(Animal.Type.SPIDER) || o.type().equals(Animal.Type.DOG))
                .collect(Collectors.groupingBy(Animal::type));
        return mapOfSpidersAndDogs.get(Animal.Type.DOG).stream().filter(Animal::bites).toList().size()
            < mapOfSpidersAndDogs.get(Animal.Type.SPIDER).stream().filter(Animal::bites).toList().size();
    }

    //Ready
    public static Animal heaviestFishFromAllListsTask18(List<List<Animal>> listOfListsOfAnimals) {
        List<Animal> myListOfAnimals = new ArrayList<>();
        for (List<Animal> o : listOfListsOfAnimals) {
            myListOfAnimals.addAll(o);
        }
        return myListOfAnimals.stream().filter(o -> o.type().equals(Animal.Type.FISH))
            .sorted((o1, o2) -> o2.weight() - o1.weight())
            .toList()
            .get(0);
    }

}
