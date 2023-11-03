package edu.project2labyrinth;

public class Labyrinth {
    protected final Cell[][] matrix;
    public Labyrinth() {
        matrix = new Cell[15][15];
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                matrix[i][j] = new Cell(i, j);
            }
        }
    }


}
