package edu.hw4;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Tasks {

    public static List<Animal> heightSortingTask1(List<Animal> listOfAnimals) {
        Comparator<Animal> heightComparator = Comparator.comparingInt(Animal::height);
        listOfAnimals.sort(heightComparator);
        return listOfAnimals;
    }

    public static List<Animal> weightSortingTask2(List<Animal> listOfAnimals) {
        Comparator<Animal> weightComparator = (o1, o2) -> o2.weight() - o1.weight();
        listOfAnimals.sort(weightComparator);
        return listOfAnimals;
    }

    //TODO task3 сделать Map<Animal.Type, Integer>
    public static Map<Animal.Type, Integer> typesOfAnimalsTask3(List<Animal> listOfAnimals) {
        Map<Animal.Type, Integer> mapOfAnimalsTypes = new HashMap<>() {
        };

        for (Animal animal : listOfAnimals) {
            if (mapOfAnimalsTypes.containsKey(animal.type())) {
                mapOfAnimalsTypes.replace(
                    animal.type(),
                    mapOfAnimalsTypes.get(animal.type()) + 1
                );
            } else {
                mapOfAnimalsTypes.put(animal.type(), 1);
            }
        }
        return mapOfAnimalsTypes;
    }

    public static Animal longestNameAnimalTask4(List<Animal> listOfAnimals) {
        Comparator<Animal> nameComparator = Comparator.comparing(Animal::name);
        listOfAnimals.sort(nameComparator);
        return listOfAnimals.getFirst();
    }

    //TODO task5 доделать, если равное количество
    public static Animal.Sex Task5(List<Animal> listOfAnimals) {
        int countF = 0;
        int countM = 0;
        for (Animal animal : listOfAnimals) {
            if (animal.sex().equals(Animal.Sex.F)) {
                countF += 1;
            } else {
                countM += 1;
            }
        }
        if (countF > countM) {
            return Animal.Sex.F;
        }
        if (countF < countM) {
            return Animal.Sex.M;
        }
        return null;
    }

    //TODO task6 сделать Map<Animal.Type, Animal> если не сработает
    public static Map<Animal.Type, Animal> heaviestAnimalsTask6(List<Animal> listOfAnimals) {
        Map<Animal.Type, Animal> mapOfHeaviestAnimals = new HashMap<>() {
        };

        for (Animal animal : listOfAnimals) {
            if (mapOfHeaviestAnimals.containsKey(animal.type()) &&
                animal.weight() > mapOfHeaviestAnimals.get(animal.type()).weight()) {
                mapOfHeaviestAnimals.replace(
                    animal.type(),
                    animal
                );
            } else {
                mapOfHeaviestAnimals.put(animal.type(), animal);
            }
        }
        return mapOfHeaviestAnimals;
    }

    public static Animal oldestAnimalTask7(List<Animal> listOfAnimals, int k) {
        Comparator<Animal> ageComparator = (o1, o2) -> o2.age() - o1.age();
        listOfAnimals.sort(ageComparator);
        return listOfAnimals.get(k - 1);
    }

    //TODO task8 мб сделать исключение, если k <= 0
    public static Optional<Animal> heaviestAnimalFromKTask8(List<Animal> listOfAnimals, int k) {
        if (k <= 0) {
            return Optional.empty();
        }
        Comparator<Animal> weightComparator = (o1, o2) -> o2.weight() - o1.weight();
        listOfAnimals.sort(weightComparator);
        for (Animal animal : listOfAnimals) {
            if (animal.weight() < k) {
                return Optional.of(animal);
            }
        }
        return Optional.empty();
    }

    public static Integer summaryOfPawsTask9(List<Animal> listOfAnimals) {
        int result = 0;
        for (Animal animal : listOfAnimals) {
            result += animal.paws();
        }
        return result;
    }

    //TODO task10 сделать List<Animal> если не сработает
    public static List<Animal> listOfAnimalsAgeIsNotEqualsPawsTask10(List<Animal> listOfAnimals) {
        List<Animal> newList = new ArrayList<>();
        for (Animal animal : listOfAnimals) {
            if (animal.age() > animal.paws()) {
                newList.add(animal);
            }
        }
        return newList;
    }

    //TODO task11 сделать List<Animal> если не сработает + animal.bites() != null
    public static List<Animal> listOfAnimalsWhoBitesAndHeightMoreHundredTask11(List<Animal> listOfAnimals) {
        final int HUNDRED = 100;
        List<Animal> newList = new ArrayList<>();
        for (Animal animal : listOfAnimals) {
            if ((animal.bites().equals(null) || animal.bites().equals(true)) && animal.height() > HUNDRED) {
                newList.add(animal);
            }
        }
        return newList;
    }

    public static Integer countOfAnimalsWeightMoreHeightTask12(List<Animal> listOfAnimals) {
        int result = 0;
        for (Animal animal : listOfAnimals) {
            if (animal.weight() > animal.height()) {
                result += 1;
            }
        }
        return result;
    }

    //TODO task13 сделать List<Animal> если не сработает
    public static List<Animal> listOfAnimalsNamesMoreTwoWordsTask13(List<Animal> listOfAnimals) {
        List<Animal> newList = new ArrayList<>();
        for (Animal animal : listOfAnimals) {
            if (animal.name().indexOf(' ') != -1) {
                newList.add(animal);
            }
        }
        return newList;
    }

    public static Boolean ifDogHeightMoreKTask14(List<Animal> listOfAnimals, int k) {
        for (Animal animal : listOfAnimals) {
            if (animal.type().equals(Animal.Type.DOG) && animal.height() > k) {
                return true;
            }
        }
        return false;
    }

    //TODO task15 сделать Map<Animal.Type, Integer> если не сработает
    public static Map<Animal.Type, Integer> summaryWeightTask15(List<Animal> listOfAnimals, int k, int i) {
        Map<Animal.Type, Integer> mapOfSummaryWeightOfAnimalType = new HashMap<>();
        for (Animal animal : listOfAnimals) {
            if (mapOfSummaryWeightOfAnimalType.containsKey(animal.type()) && animal.weight() > k
                && animal.weight() < i) {
                mapOfSummaryWeightOfAnimalType.replace(
                    animal.type(),
                    mapOfSummaryWeightOfAnimalType.get(animal.type()) + animal.weight()
                );
            } else {
                if (animal.weight() > k && animal.weight() < i) {
                    mapOfSummaryWeightOfAnimalType.put(animal.type(), animal.weight());
                }
            }
        }
        return mapOfSummaryWeightOfAnimalType;
    }

    public static List<Animal> sortedListOfAnimalsTask16(List<Animal> listOfAnimals) {
        Comparator<Animal> myComparator = Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name);
        listOfAnimals.sort(myComparator);
        return listOfAnimals;
    }

    public static Boolean ifSpidersBitesMoreDogsTask17(List<Animal> listOfAnimals) {
        boolean isDogs = false;
        boolean isSpiders = false;
        int countOfSpidersWhoBites = 0;
        int countOfDogsWhoBites = 0;
        for (Animal animal : listOfAnimals) {
            if (animal.type().equals(Animal.Type.DOG)) {
                isDogs = true;
                if (animal.bites().equals(true)) {
                    countOfDogsWhoBites += 1;
                }
            }
            if (animal.type().equals(Animal.Type.SPIDER)) {
                isSpiders = true;
                if (animal.bites().equals(true)) {
                    countOfSpidersWhoBites += 1;
                }
            }
        }
        return isDogs && isSpiders && countOfDogsWhoBites > countOfSpidersWhoBites;
    }

    public static Animal heaviest
}
