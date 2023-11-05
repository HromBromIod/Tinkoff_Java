package edu.project2labyrinth;

public class TestClass {
    public static void main(String[] args) {
        Labyrinth lab = new Labyrinth(39, 39);
        lab.createLabyrinth();
        lab.prettyPrint();
        lab.findTheWay(new Cell(1, 1), new Cell(33, 17));
        lab.prettyPrint();
    }
}
