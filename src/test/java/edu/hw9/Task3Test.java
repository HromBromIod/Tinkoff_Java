package edu.hw9;

import edu.hw9.Task3.Solver;
import edu.project2labyrinth.Cell;
import edu.project2labyrinth.Labyrinth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import static edu.hw9.Task3.Solver.compareCells;
import static edu.project2labyrinth.Labyrinth.generateLabyrinth;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private Task3Test() {
    }

    @Test
    @DisplayName("sdfg")
    void test() {
        Cell[][] maze = new Cell[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                maze[i][j] = new Cell(i, j);
            }
        }

        maze[1][1].setTypeToWay();
        maze[1][3].setTypeToWay();
        maze[1][4].setTypeToWay();
        maze[1][5].setTypeToWay();
        maze[1][6].setTypeToWay();
        maze[1][7].setTypeToWay();

        maze[2][1].setTypeToWay();
        maze[2][3].setTypeToWay();
        maze[2][5].setTypeToWay();
        maze[2][7].setTypeToWay();

        maze[3][1].setTypeToWay();
        maze[3][2].setTypeToWay();
        maze[3][3].setTypeToWay();
        maze[3][5].setTypeToWay();
        maze[3][7].setTypeToWay();

        maze[4][1].setTypeToWay();
        maze[4][7].setTypeToWay();

        maze[5][1].setTypeToWay();
        maze[5][3].setTypeToWay();
        maze[5][5].setTypeToWay();
        maze[5][6].setTypeToWay();
        maze[5][7].setTypeToWay();

        maze[6][3].setTypeToWay();
        maze[6][5].setTypeToWay();
        maze[6][7].setTypeToWay();

        maze[7][1].setTypeToWay();
        maze[7][2].setTypeToWay();
        maze[7][3].setTypeToWay();
        maze[7][4].setTypeToWay();
        maze[7][5].setTypeToWay();
        maze[7][7].setTypeToWay();

        Labyrinth labyrinth = new Labyrinth(maze, 9, 9);
        List<Cell> wayLabyrinth = labyrinth.findTheWay(new Cell(1, 1), new Cell(7, 7));

        Solver solver = new Solver(maze, 9, 9);
        solver.setStartEndCells(new Cell(1, 1), new Cell(7, 7));
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        List<Cell> waySolver = forkJoinPool.invoke(solver);
        boolean actual = true;
        boolean expected = true;
        for (int i = 0, j = 0; i < wayLabyrinth.size() && j < waySolver.size() && actual; ++i, ++j) {
            if (!compareCells(wayLabyrinth.get(i), waySolver.get(j))) {
                actual = false;
            }
        }
        assertEquals(expected, actual);
        forkJoinPool.close();
    }
}
