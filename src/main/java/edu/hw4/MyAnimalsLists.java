package edu.hw4;

import java.util.Arrays;
import java.util.List;

public class MyAnimalsLists {
    private MyAnimalsLists() {
    }

    public static final List<Animal> MYFIRSTLIST = Arrays.asList(
        new Animal("мяу", Animal.Type.CAT, Animal.Sex.M, 6, 32, 5000, true),
        new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 9, 57, 7001, true),
        new Animal("Чиж CO", Animal.Type.BIRD, Animal.Sex.F, 7, 46, 1230, false),
        new Animal("Henry", Animal.Type.FISH, Animal.Sex.F, 2, 5, 120, false),
        new Animal("Douglas", Animal.Type.BIRD, Animal.Sex.M, 11, 34, 785, true),
        new Animal("Аркадий Павук", Animal.Type.SPIDER, Animal.Sex.M, 3, 15, 220, true),
        new Animal("Locker", Animal.Type.FISH, Animal.Sex.M, 3, 12, 245, true),
        new Animal("Paper", Animal.Type.DOG, Animal.Sex.F, 5, 58, 6708, true)
    );

    public static final List<Animal> MYSECONDLIST = Arrays.asList(
        new Animal("мяу", Animal.Type.CAT, Animal.Sex.F, 4, 32, 5000, true),
        new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 9, 101, 7000, true),
        new Animal("Чиж CO", Animal.Type.BIRD, Animal.Sex.M, 7, 46, 1230, false),
        new Animal("Harry", Animal.Type.FISH, Animal.Sex.M, 2, 5, 120, false),
        new Animal("Douglas", Animal.Type.BIRD, Animal.Sex.F, 5, 34, 785, true),
        new Animal("Locker", Animal.Type.FISH, Animal.Sex.F, 3, 12, 10, true),
        new Animal("Paper", Animal.Type.DOG, Animal.Sex.M, 5, 90, 6708, true)
    );

    public static final List<List<Animal>> listOfLists = Arrays.asList(MYFIRSTLIST, MYSECONDLIST);

    public static final List<Animal> MYBADLIST = Arrays.asList(
        new Animal(null, Animal.Type.CAT, Animal.Sex.F, 6, 32, 5000, true),
        new Animal("Dog", null, Animal.Sex.F, 9, 57, 7000, true),
        new Animal("Чиж CO", Animal.Type.BIRD, null, 7, 46, 1230, false),
        new Animal("Harry", Animal.Type.FISH, Animal.Sex.M, -1, 5, 120, false),
        new Animal("Douglas", Animal.Type.BIRD, Animal.Sex.F, 11, -1, 785, true),
        new Animal("Locker", Animal.Type.FISH, Animal.Sex.F, 3, 12, -1, true),
        new Animal("", null, null, -1, -1, -1, false)
    );

    public static final List<Animal> emptyList = List.of();
}
