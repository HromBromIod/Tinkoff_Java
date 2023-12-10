package edu.hw9.Task3;

import edu.project2labyrinth.Cell;
import edu.project2labyrinth.TypeOfCell;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings({"CyclomaticComplexity", "RegexpSinglelineJava"})
public class Solver extends RecursiveTask<List<Cell>> {
    private static final int MAX_HEIGHT_OR_WIDTH = 39;
    private static final int MIN_HEIGHT_OR_WIDTH = 9;
    private final int height;
    private final int width;
    protected final Cell[][] maze;
    private Cell start;
    private Cell end;

    public Solver(int n, int m) throws IllegalArgumentException {
        if (n < MIN_HEIGHT_OR_WIDTH || n > MAX_HEIGHT_OR_WIDTH || m < MIN_HEIGHT_OR_WIDTH || m > MAX_HEIGHT_OR_WIDTH) {
            throw new IllegalArgumentException();
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
        createLabyrinth();
    }

    public Solver(Cell[][] personMatrix, int n, int m) throws IllegalArgumentException {
        if (personMatrix == null || personMatrix.length < MIN_HEIGHT_OR_WIDTH
            || personMatrix.length > MAX_HEIGHT_OR_WIDTH) {
            throw new IllegalArgumentException();
        }
        for (Cell[] matrix : personMatrix) {
            if ((matrix.length < MIN_HEIGHT_OR_WIDTH || matrix.length > MAX_HEIGHT_OR_WIDTH)) {
                throw new IllegalArgumentException();
            }
        }
        if (n < MIN_HEIGHT_OR_WIDTH || n > MAX_HEIGHT_OR_WIDTH || m < MIN_HEIGHT_OR_WIDTH || m > MAX_HEIGHT_OR_WIDTH) {
            throw new IllegalArgumentException();
        } else {
            height = n;
            width = m;
            maze = new Cell[height][width];
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    maze[i][j] = new Cell(personMatrix[i][j]);
                }
            }
        }
    }

    public static Solver generateLabyrinth() {
        final int MID_SHIFT_VALUE = 26;
        final int SCATTER = 6;
        Random random = new Random();
        int randomInt = random.nextInt(MIN_HEIGHT_OR_WIDTH, MAX_HEIGHT_OR_WIDTH + 1);
        final int height = randomInt % 2 == 0 ? randomInt + 1 : randomInt;
        final int width;
        if (height > MID_SHIFT_VALUE) {
            randomInt = random.nextInt(0, SCATTER);
            width = height - (randomInt % 2 == 0 ? randomInt : randomInt + 1);
        } else {
            randomInt = random.nextInt(0, SCATTER);
            width = height + (randomInt % 2 == 0 ? randomInt : randomInt + 1);
        }
        return new Solver(height, width);
    }

    private boolean isKLeftNeighbourWay(Cell cell, int k) {
        return maze[cell.getX() - k][cell.getY()].getType().equals(TypeOfCell.WAY);
    }

    private boolean isKLeftNeighbourVisited(Cell cell, int k) {
        return maze[cell.getX() - k][cell.getY()].getIsVisited();
    }

    private boolean isKRightNeighbourWay(Cell cell, int k) {
        return maze[cell.getX() + k][cell.getY()].getType().equals(TypeOfCell.WAY);
    }

    private boolean isKRightNeighbourVisited(Cell cell, int k) {
        return maze[cell.getX() + k][cell.getY()].getIsVisited();
    }

    private boolean isKUnderNeighbourWay(Cell cell, int k) {
        return maze[cell.getX()][cell.getY() - k].getType().equals(TypeOfCell.WAY);
    }

    private boolean isKUnderNeighbourVisited(Cell cell, int k) {
        return maze[cell.getX()][cell.getY() - k].getIsVisited();
    }

    private boolean isKTopNeighbourWay(Cell cell, int k) {
        return maze[cell.getX()][cell.getY() + k].getType().equals(TypeOfCell.WAY);
    }

    private boolean isKTopNeighbourVisited(Cell cell, int k) {
        return maze[cell.getX()][cell.getY() + k].getIsVisited();
    }

    private List<Cell> getCurrentNeighbours(Cell myCell, int k) {
        List<Cell> listOfNeighbours = new ArrayList<>();
        int x = myCell.getX();
        int y = myCell.getY();
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

    private void createLabyrinth() {
        Random random = new Random();
        int randomInt = random.nextInt(1, height - 2);
        int x = randomInt % 2 == 0 ? randomInt + 1 : randomInt;
        randomInt = random.nextInt(1, width - 2);
        int y = randomInt % 2 == 0 ? randomInt + 1 : randomInt;
        List<Cell> myList = new ArrayList<>();
        Cell myCell = maze[x][y];
        myCell.setTypeToWay();
        do {
            x = myCell.getX();
            y = myCell.getY();
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
    }

    private void addNeighboursInStack(List<Cell> neighbours, Stack<Cell> stack) {
        for (Cell o : neighbours) {
            stack.push(o);
        }
    }

    public List<Cell> findTheWay() {
        clearWay();
        Cell firstCell = maze[start.getX()][start.getY()];
        Cell lastCell = maze[end.getX()][end.getY()];
        if (firstCell.getType().equals(TypeOfCell.WALL) || lastCell.getType().equals(TypeOfCell.WALL)) {
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
        neighbours = myStack.stream().filter(Cell::getIsVisited).toList();
        for (Cell o : neighbours) {
            o.setTypeToRoute();
        }
        return neighbours;
    }

    private void clearWay() {
        for (int i = 1; i < height - 1; ++i) {
            for (int j = 1; j < width - 1; ++j) {
                if (maze[i][j].getType().equals(TypeOfCell.ROUTE) || maze[i][j].getIsVisited()) {
                    maze[i][j].setTypeToWay();
                    maze[i][j].makeUnvisited();
                }
            }
        }
    }

    public void prettyPrint() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (maze[i][j].getType().equals(TypeOfCell.WALL)) {
                    System.out.print("[#]");
                }
                if (maze[i][j].getType().equals(TypeOfCell.ROUTE)) {
                    System.out.print(" + ");
                }
                if (maze[i][j].getType().equals(TypeOfCell.WAY)) {
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }

    public static boolean compareCells(Cell left, Cell right) {
        return left.getType().equals(right.getType()) && left.getIsVisited() == right.getIsVisited()
            && left.getX() == right.getX() && left.getY() == right.getY();
    }

    public void setStartEndCells(Cell cellA, Cell cellB) {
        start = new Cell(cellA);
        end = new Cell(cellB);
    }

    @Override
    protected List<Cell> compute() {
        return findTheWay();
    }
}
