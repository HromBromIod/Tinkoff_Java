package edu.project2labyrinth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LabyrinthTests {
    private LabyrinthTests() {
    }

    @Test
    @DisplayName("Тест, когда путь не находится, тк введены координаты стены")
    void testWall() {
        Labyrinth labyrinth = new Labyrinth();
        labyrinth.createLabyrinth();
        assertNull(labyrinth.findTheWay(new Cell(0, 0), new Cell(1, 1)));
    }

    @Test
    @DisplayName("Нахождение пути из (x1,y1) в (x2,y2)")
    void testFindWay() {
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
        List<Cell> actual = labyrinth.findTheWay(new Cell(1, 1), new Cell(3, 7));
        List<Cell> expected = Arrays.asList(
            maze[1][1],
            maze[2][1],
            maze[3][1],
            maze[3][2],
            maze[3][3],
            maze[2][3],
            maze[1][3],
            maze[1][4],
            maze[1][5],
            maze[1][6],
            maze[1][7],
            maze[2][7],
            maze[3][7]
        );
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка построения заданного лабиринта")
    void testCheckCreate() {
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
        assertEquals(maze, labyrinth.maze);
    }

    @Test
    @DisplayName("Проверка на очистку лабиринта от найденного пути")
    void testClear() {
        Labyrinth labyrinth = new Labyrinth(15, 15);
        labyrinth.createLabyrinth();
        List<Cell> actual = labyrinth.findTheWay(new Cell(3, 3), new Cell(13, 13));
        labyrinth.clearWay(actual);
        actual = actual.stream().filter(o -> o.type.equals(TypeOfCell.ROUTE)).toList();
        List<Cell> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Неверные параметры при иниицализации лабиринта (размерность)")
    void testBadParams1() {
        assertThrows(IllegalArgumentException.class, () -> new Labyrinth(4, 41));
    }

    @Test
    @DisplayName("Неверные параметры при иниицализации лабиринта (матрица с исходным лабиринтом пуста)")
    void testBadParams2() {
        Cell[][] maze = new Cell[][] {};
        assertThrows(IllegalArgumentException.class, () -> new Labyrinth(maze, 13, 13));
    }

    @Test
    @DisplayName("Неверные параметры при иниицализации лабиринта (матрица с исходным лабиринтом = null)")
    void testBadParams3() {
        assertThrows(IllegalArgumentException.class, () -> new Labyrinth(null, 13, 13));
    }
}
