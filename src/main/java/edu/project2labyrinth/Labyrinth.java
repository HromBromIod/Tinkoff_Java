package edu.project2labyrinth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Labyrinth {
    private final int height;
    private final int width;
    protected final Cell[][] maze;

    public Labyrinth() {
        Random random = new Random();
        int randomInt = random.nextInt(9, 40);
        height = randomInt % 2 == 0 ? randomInt + 1 : randomInt;
        if (height > 26) {
            randomInt = random.nextInt(0, 6);
            width = height + (randomInt % 2 == 0 ? randomInt : randomInt + 1);
        } else {
            randomInt = random.nextInt(0, 6);
            width = height - (randomInt % 2 == 0 ? randomInt : randomInt + 1);
        }
        maze = new Cell[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                maze[i][j] = new Cell(i, j);
            }
        }
    }

    public Labyrinth(int n, int m) {
        height = n;
        width = m;
        maze = new Cell[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                maze[i][j] = new Cell(i, j);
            }
        }
    }

    public Labyrinth(Cell[][] personMatrix, int n, int m) {
        height = n;
        width = m;
        maze = personMatrix;
    }

    private List<Cell> getCurrentNeighbours(Cell myCell, int k) {
        List<Cell> listOfNeighbours = new ArrayList<>();
        int x = myCell.x;
        int y = myCell.y;
        if (x > 1 && maze[x - k][y].type.equals(TypeOfCell.WAY) && (k == 2 || k == 1 && !maze[x - k][y].isVisited)) {
            listOfNeighbours.add(maze[x - 1][y]);
        }
        if (x < height - 2 && maze[x + k][y].type.equals(TypeOfCell.WAY) &&
            (k == 2 || k == 1 && !maze[x + k][y].isVisited)) {
            listOfNeighbours.add(maze[x + 1][y]);
        }
        if (y > 1 && maze[x][y - k].type.equals(TypeOfCell.WAY) && (k == 2 || k == 1 && !maze[x][y - k].isVisited)) {
            listOfNeighbours.add(maze[x][y - 1]);
        }
        if (y < width - 2 && maze[x][y + k].type.equals(TypeOfCell.WAY) &&
            (k == 2 || k == 1 && !maze[x][y + k].isVisited)) {
            listOfNeighbours.add(maze[x][y + 1]);
        }
        return listOfNeighbours;
    }

    private void connect(Cell myCell) {
        Random random = new Random();
        List<Cell> canBeWays = getCurrentNeighbours(myCell, 2);
        canBeWays.get(random.nextInt(canBeWays.size())).setTypeToWay();
    }

    public void createLabyrinth() {
        Random random = new Random();
        int randomInt = random.nextInt(1, height - 2);
        int x = randomInt % 2 == 0 ? randomInt + 1 : randomInt;
        randomInt = random.nextInt(2, width - 2);
        int y = randomInt % 2 == 0 ? randomInt + 1 : randomInt;
        List<Cell> myList = new ArrayList<>();
        Cell myCell = maze[x][y];
        myCell.setTypeToWay();
        do {
            x = myCell.x;
            y = myCell.y;
            if (x > 1 && maze[x - 2][y].type.equals(TypeOfCell.WALL) && !myList.contains(maze[x - 2][y])) {
                myList.add(maze[x - 2][y]);
            }
            if (x < height - 2 && maze[x + 2][y].type.equals(TypeOfCell.WALL) && !myList.contains(maze[x + 2][y])) {
                myList.add(maze[x + 2][y]);
            }
            if (y > 1 && maze[x][y - 2].type.equals(TypeOfCell.WALL) && !myList.contains(maze[x][y - 2])) {
                myList.add(maze[x][y - 2]);
            }
            if (y < width - 2 && maze[x][y + 2].type.equals(TypeOfCell.WALL) && !myList.contains(maze[x][y + 2])) {
                myList.add(maze[x][y + 2]);
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
        Cell firstCell = maze[cellA.x][cellA.y];
        Cell lastCell = maze[cellB.x][cellB.y];
        if (firstCell.type.equals(TypeOfCell.WALL) || lastCell.type.equals(TypeOfCell.WALL)) {
            return null;
        }
        Cell currentCell = firstCell;
        Stack<Cell> myStack = new Stack<>();
        myStack.push(currentCell);
        List<Cell> neighbours;
        while (!currentCell.equals(lastCell)) {
            neighbours = getCurrentNeighbours(currentCell, 1);
            currentCell.makeVisited();
            if (!neighbours.isEmpty()) {
                for (Cell o : neighbours) {
                    myStack.push(o);
                }
            } else {
                myStack.pop();
            }
            currentCell = myStack.peek();
        }
        lastCell.setTypeToRoute();
        neighbours = myStack.stream().filter(o -> o.isVisited).toList().reversed();
        for (Cell o : neighbours) {
            o.setTypeToRoute();
        }
        return neighbours;
    }

    public void clearWay(List<Cell> way) {
        if (way != null) {
            for (Cell o : way) {
                o.setTypeToWay();
                o.makeUnvisited();
            }
        }
    }

    public void prettyPrint() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (maze[i][j].type.equals(TypeOfCell.WALL)) {
                    System.out.print("[#]");
                }
                if (maze[i][j].type.equals(TypeOfCell.ROUTE)) {
                    System.out.print(" + ");
                }
                if (maze[i][j].type.equals(TypeOfCell.WAY)) {
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }
}
