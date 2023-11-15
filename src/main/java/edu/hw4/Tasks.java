package edu.hw4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import static edu.hw4.ValidationError.animalErrors;
import static edu.hw4.ValidationError.animalErrorsToString;

public class Tasks {
    private Tasks() {
    }

    public static List<Animal> sortAnimalsByHeight(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    public static List<Animal> weightSortingTask2(List<Animal> listOfAnimals, int k) {
        return listOfAnimals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed()).limit(k).toList();
    }

    public static Map<Animal.Type, Long> typesOfAnimalsTask3(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal longestNameAnimalTask4(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().max(Comparator.comparing(o -> o.name().length())).orElse(null);
    }

    public static Animal.Sex maxAnimalSexTask5(List<Animal> listOfAnimals) {
        Map<Animal.Sex, Long> mapAnimalSex =
            listOfAnimals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        if (mapAnimalSex.get(Animal.Sex.F) > mapAnimalSex.get(Animal.Sex.M)) {
            return Animal.Sex.F;
        }
        return Animal.Sex.M;
    }

    public static Map<Animal.Type, Animal> heaviestAnimalsTask6(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().collect(Collectors.toMap(
            Animal::type,
            Function.identity(),
            BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
        ));
    }

    public static Animal oldestAnimalTask7(List<Animal> listOfAnimals, int k) {
        return k > 0 && k < listOfAnimals.size()
            ?
            listOfAnimals.stream().sorted(Comparator.comparing(Animal::age).reversed()).skip(k - 1).findFirst().get() :
            null;
    }

    public static Optional<Animal> heaviestAnimalFromKTask8(List<Animal> listOfAnimals, int k) {
        return k <= 0 ? Optional.empty()
            : listOfAnimals.stream().filter(o -> o.height() < k).max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer summaryOfPawsTask9(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> listOfAnimalsAgeIsNotEqualsPawsTask10(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().filter(o -> o.age() != o.paws()).toList();
    }

    public static List<Animal> listOfAnimalsWhoBitesAndHeightMoreHundredTask11(List<Animal> listOfAnimals) {
        final int HUNDRED = 100;
        return listOfAnimals.stream().filter(o -> o.bites() && o.height() > HUNDRED).toList();
    }

    public static Integer countOfAnimalsWeightMoreHeightTask12(List<Animal> listOfAnimals) {
        return (int) listOfAnimals.stream().filter(o -> o.weight() > o.height()).count();
    }

    public static List<Animal> listOfAnimalsNamesMoreTwoWordsTask13(List<Animal> listOfAnimals) {
        return listOfAnimals.stream().filter(o -> o.name().trim().indexOf(' ') != -1).toList();
    }

    public static Boolean ifDogHeightMoreKTask14(List<Animal> listOfAnimals, int k) {
        return k <= 0 ? null
            : listOfAnimals.stream().anyMatch(o -> o.type().equals(Animal.Type.DOG) && o.height() > k);
    }

    public static Map<Animal.Type, Integer> summaryWeightTask15(List<Animal> listOfAnimals, int k, int i) {
        return k < i && k >= 0 ? listOfAnimals.stream().filter(o -> o.age() >= k && o.age() <= i)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight))) : null;
    }

    public static List<Animal> sortedListOfAnimalsTask16(List<Animal> listOfAnimals) {
        return listOfAnimals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name)).toList();
    }

    public static Boolean ifSpidersBitesMoreDogsTask17(List<Animal> listOfAnimals) {
        Map<Animal.Type, List<Animal>> mapOfSpidersAndDogs =
            listOfAnimals.stream().filter(o -> o.type().equals(Animal.Type.SPIDER) || o.type().equals(Animal.Type.DOG))
                .collect(Collectors.groupingBy(Animal::type));
        return mapOfSpidersAndDogs.get(Animal.Type.DOG).stream().filter(Animal::bites).count()
            < mapOfSpidersAndDogs.get(Animal.Type.SPIDER).stream().filter(Animal::bites).count();
    }

    public static Animal heaviestFishFromAllListsTask18(List<List<Animal>> listOfListsOfAnimals) {
        return listOfListsOfAnimals.stream().flatMap(Collection::stream).filter(o -> o.type().equals(Animal.Type.FISH))
            .max(Comparator.comparing(Animal::weight)).get();
    }

    public static Map<String, Set<ValidationError>> errorsInAnimalsTask19(List<Animal> listOfAnimals) {
        Map<String, Set<ValidationError>> mapOfErrorAnimals = new HashMap<>();
        listOfAnimals.forEach((animal -> {
            if (!(animalErrors(animal).isEmpty())) {
                mapOfErrorAnimals.put(animal.name(), animalErrors(animal));
            }
        }));
        return mapOfErrorAnimals;
    }

    public static Map<String, String> errorsInAnimalsToStringTask20(List<Animal> listOfAnimals) {
        Map<String, String> mapOfErrorAnimals = new HashMap<>();
        listOfAnimals.forEach((animal -> {
            if (!(animalErrors(animal).isEmpty())) {
                mapOfErrorAnimals.put(animal.name(), animalErrorsToString(animalErrors(animal)));
            }
        }));
        return mapOfErrorAnimals;
    }
}
