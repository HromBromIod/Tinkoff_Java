package edu.hw9;

import edu.hw9.Task3.Solver;
import edu.hw9.Task3.Cell;
import edu.hw9.Task3.Labyrinth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ForkJoinPool;
import static edu.hw9.Task3.Cell.compare;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private Task3Test() {
    }

    @Test
    @DisplayName("solver's way equals labyrinth's way")
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
        List<Cell> way = labyrinth.findTheWay(new Cell(1, 1), new Cell(7, 7));

        Labyrinth labyrinthWithSolver = new Labyrinth(maze, 9, 9);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Solver solver = new Solver(new Stack<>(), labyrinthWithSolver, maze[1][1], maze[7][7]);
        List<Cell> wayFromSolver = forkJoinPool.invoke(solver);

        boolean actual = true;
        boolean expected = true;
        for (int i = 0, j = 0; i < way.size() && j < wayFromSolver.size() && actual; ++i, ++j) {
            if (!compare(way.get(i), wayFromSolver.get(j))) {
                actual = false;
            }
        }
        assertEquals(expected, actual);
    }
}
