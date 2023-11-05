package edu.project2labyrinth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

@SuppressWarnings({"RegexpSinglelineJava", "CyclomaticComplexity"})
public class Labyrinth {
    private final int MAXHEIGHTORWIDTH = 39;
    private final int MINHEIGHTORWIDTH = 9;
    private final int height;
    private final int width;
    protected final Cell[][] maze;

    public Labyrinth() {
        final int TWENTY_SIX = 26;
        final int SIX = 6;
        Random random = new Random();
        int randomInt = random.nextInt(MINHEIGHTORWIDTH, MAXHEIGHTORWIDTH + 1);
        height = randomInt % 2 == 0 ? randomInt + 1 : randomInt;
        if (height > TWENTY_SIX) {
            randomInt = random.nextInt(0, SIX);
            width = height + (randomInt % 2 == 0 ? randomInt : randomInt + 1);
        } else {
            randomInt = random.nextInt(0, SIX);
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
        if (n < MINHEIGHTORWIDTH || n > MAXHEIGHTORWIDTH || m < MINHEIGHTORWIDTH || m > MAXHEIGHTORWIDTH) {
            height = 0;
            width = 0;
            maze = null;
        } else {
            height = n;
            width = m;
            maze = new Cell[height][width];
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    maze[i][j] = new Cell(i, j);
                }
            }
        }
    }

    public Labyrinth(Cell[][] personMatrix, int n, int m) {
        boolean flag = personMatrix == null || personMatrix.length < MINHEIGHTORWIDTH
            || personMatrix.length > MAXHEIGHTORWIDTH;
        for (int i = 0; !flag && i < personMatrix.length; ++i) {
            if ((personMatrix[i].length < MINHEIGHTORWIDTH || personMatrix[i].length > MAXHEIGHTORWIDTH)) {
                flag = true;
                break;
            }
        }
        if (n < MINHEIGHTORWIDTH || n > MAXHEIGHTORWIDTH || m < MINHEIGHTORWIDTH || m > MAXHEIGHTORWIDTH
            || flag) {
            height = 0;
            width = 0;
            maze = null;
        } else {
            height = n;
            width = m;
            maze = personMatrix;
        }
    }

    private boolean isKLeftNeighbourWay(Cell cell, int k) {
        return maze[cell.x - k][cell.y].type.equals(TypeOfCell.WAY);
    }

    private boolean isKLeftNeighbourVisited(Cell cell, int k) {
        return maze[cell.x - k][cell.y].isVisited;
    }

    private boolean isKRightNeighbourWay(Cell cell, int k) {
        return maze[cell.x + k][cell.y].type.equals(TypeOfCell.WAY);
    }

    private boolean isKRightNeighbourVisited(Cell cell, int k) {
        return maze[cell.x + k][cell.y].isVisited;
    }

    private boolean isKUnderNeighbourWay(Cell cell, int k) {
        return maze[cell.x][cell.y - k].type.equals(TypeOfCell.WAY);
    }

    private boolean isKUnderNeighbourVisited(Cell cell, int k) {
        return maze[cell.x][cell.y - k].isVisited;
    }

    private boolean isKTopNeighbourWay(Cell cell, int k) {
        return maze[cell.x][cell.y + k].type.equals(TypeOfCell.WAY);
    }

    private boolean isKTopNeighbourVisited(Cell cell, int k) {
        return maze[cell.x][cell.y + k].isVisited;
    }

    private List<Cell> getCurrentNeighbours(Cell myCell, int k) {
        List<Cell> listOfNeighbours = new ArrayList<>();
        int x = myCell.x;
        int y = myCell.y;
        if (x > 1 && isKLeftNeighbourWay(myCell, k) && (k == 2 || k == 1 && !isKLeftNeighbourVisited(myCell, k))) {
            listOfNeighbours.add(maze[x - 1][y]);
        }
        if (x < height - 2 && isKRightNeighbourWay(myCell, k)
            && (k == 2 || k == 1 && !isKRightNeighbourVisited(myCell, k))) {
            listOfNeighbours.add(maze[x + 1][y]);
        }
        if (y > 1 && isKUnderNeighbourWay(myCell, k) && (k == 2 || k == 1 && !isKUnderNeighbourVisited(myCell, k))) {
            listOfNeighbours.add(maze[x][y - 1]);
        }
        if (y < width - 2 && isKTopNeighbourWay(myCell, k)
            && (k == 2 || k == 1 && !isKTopNeighbourVisited(myCell, k))) {
            listOfNeighbours.add(maze[x][y + 1]);
        }
        return listOfNeighbours;
    }

    private void connect(Cell myCell) {
        Random random = new Random();
        List<Cell> canBeWays = getCurrentNeighbours(myCell, 2);
        canBeWays.get(random.nextInt(canBeWays.size())).setTypeToWay();
    }

    public boolean createLabyrinth() {
        if (maze == null) {
            return false;
        }
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
            if (x > 1 && !isKLeftNeighbourWay(myCell, 2) && !myList.contains(maze[x - 2][y])) {
                myList.add(maze[x - 2][y]);
            }
            if (x < height - 2 && !isKRightNeighbourWay(myCell, 2) && !myList.contains(maze[x + 2][y])) {
                myList.add(maze[x + 2][y]);
            }
            if (y > 1 && !isKUnderNeighbourWay(myCell, 2) && !myList.contains(maze[x][y - 2])) {
                myList.add(maze[x][y - 2]);
            }
            if (y < width - 2 && !isKTopNeighbourWay(myCell, 2) && !myList.contains(maze[x][y + 2])) {
                myList.add(maze[x][y + 2]);
            }
            if (!myList.isEmpty()) {
                myCell = myList.get(random.nextInt(myList.size()));
                myList.remove(myCell);
                myCell.setTypeToWay();
                connect(myCell);
            }
        } while (!myList.isEmpty());
        return true;
    }

    private void addNeighboursInStack(List<Cell> neighbours, Stack<Cell> stack) {
        for (Cell o : neighbours) {
            stack.push(o);
        }
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
                addNeighboursInStack(neighbours, myStack);
            } else {
                myStack.pop();
            }
            currentCell = myStack.peek();
        }
        lastCell.setTypeToRoute();
        lastCell.makeVisited();
        neighbours = myStack.stream().filter(o -> o.isVisited).toList();
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
