package edu.project2labyrinth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Labyrinth {
    private final int height;
    private final int width;
    protected final Cell[][] matrix;

    public Labyrinth() {
        Random random = new Random();
        int randomInt = random.nextInt(9, 40);
        height = randomInt % 2 == 0 ? randomInt + 1 : randomInt;
        if (height > 26) {
            randomInt = random.nextInt(0, 6);
            width = height + (randomInt % 2 == 0 ? randomInt : randomInt + 1);
        } else {
            randomInt = random.nextInt(0, 6);
            width = height - (randomInt % 2 == 0 ? randomInt : randomInt  + 1);
        }
        matrix = new Cell[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                matrix[i][j] = new Cell(i, j);
            }
        }
    }

    public Labyrinth(int n, int m) {
        height = n;
        width = m;
        matrix = new Cell[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                matrix[i][j] = new Cell(i, j);
            }
        }
    }

    public Labyrinth(Cell[][] personMatrix, int n, int m) {
        height = n;
        width = m;
        matrix = personMatrix;
    }

    private void connect(Cell myCell) {
        Random random = new Random();
        int x = myCell.x;
        int y = myCell.y;
        List<Cell> canBeWays = new ArrayList<>();
        if (x > 1 && matrix[x - 2][y].type.equals(TypeOfCell.WAY)) {
            canBeWays.add(matrix[x - 1][y]);
        }
        if (x < height - 2 && matrix[x + 2][y].type.equals(TypeOfCell.WAY)) {
            canBeWays.add(matrix[x + 1][y]);
        }
        if (y > 1 && matrix[x][y - 2].type.equals(TypeOfCell.WAY)) {
            canBeWays.add(matrix[x][y - 1]);
        }
        if (y < width - 2 && matrix[x][y + 2].type.equals(TypeOfCell.WAY)) {
            canBeWays.add(matrix[x][y + 1]);
        }
        canBeWays.get(random.nextInt(canBeWays.size())).setTypeToWay();
    }

    public void createLabyrinth() {
        Random random = new Random();
        int randomInt = random.nextInt(1, height - 2);
        int x = randomInt % 2 == 0 ? randomInt + 1 : randomInt;
        randomInt = random.nextInt(2, width - 2);
        int y = randomInt % 2 == 0 ? randomInt + 1 : randomInt;
        List<Cell> myList = new ArrayList<>();
        Cell myCell = matrix[x][y];
        myCell.setTypeToWay();
        do {
            x = myCell.x;
            y = myCell.y;
            if (x > 1 && matrix[x - 2][y].type.equals(TypeOfCell.WALL) && !myList.contains(matrix[x - 2][y])) {
                myList.add(matrix[x - 2][y]);
            }
            if (x < height - 2 && matrix[x + 2][y].type.equals(TypeOfCell.WALL) && !myList.contains(matrix[x + 2][y])) {
                myList.add(matrix[x + 2][y]);
            }
            if (y > 1 && matrix[x][y - 2].type.equals(TypeOfCell.WALL) && !myList.contains(matrix[x][y - 2])) {
                myList.add(matrix[x][y - 2]);
            }
            if (y < width - 2 && matrix[x][y + 2].type.equals(TypeOfCell.WALL) && !myList.contains(matrix[x][y + 2])) {
                myList.add(matrix[x][y + 2]);
            }
            if (!myList.isEmpty()) {
                myCell = myList.get(random.nextInt(myList.size()));
                myList.remove(myCell);
                myCell.setTypeToWay();
                connect(myCell);
            }
        } while (!myList.isEmpty());
    }

    public List<Cell> findTheWay(Cell cellA, Cell cellB) {
        Cell firstCell = matrix[cellA.x][cellA.y];
        Cell lastCell = matrix[cellB.x][cellB.y];
        Cell currentCell = firstCell;
        Stack<Cell> myStack = new Stack<>();
        myStack.push(currentCell);
        while (!currentCell.equals(lastCell)) {
            currentCell = myStack.peek();
            myStack.pop();

        }
        return myStack.stream().toList();
    }

    public void prettyPrint() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (matrix[i][j].type.equals(TypeOfCell.WALL)) {
                    System.out.print("[#]");
                }
                if (matrix[i][j].type.equals(TypeOfCell.ROUTE)) {
                    System.out.print(" + ");
                }
                if (matrix[i][j].type.equals(TypeOfCell.WAY)) {
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }
}
