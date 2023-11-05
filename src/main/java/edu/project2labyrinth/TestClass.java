package edu.project2labyrinth;

public class TestClass {
    public static void main(String[] args) {
        Labyrinth lab = new Labyrinth(9, 9);
        lab.createLabyrinth();
        lab.prettyPrint();
        lab.findTheWay(new Cell(1, 1), new Cell(3, 7));
        lab.prettyPrint();
    }
}
