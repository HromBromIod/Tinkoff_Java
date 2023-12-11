package edu.hw9.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("CyclomaticComplexity")
public class Solver extends RecursiveTask<List<Cell>> {
    private final Stack<Cell> cellStack;
    private final Labyrinth labyrinth;
    private final Cell start;
    private final Cell end;

    public Solver(Stack<Cell> cellStack, Labyrinth labyrinth, Cell start, Cell end) {
        this.cellStack = new Stack<>();
        this.cellStack.addAll(cellStack);
        this.labyrinth = labyrinth;
        this.start = labyrinth.maze[start.x][start.y];
        this.end = labyrinth.maze[end.x][end.y];
    }

    private List<Cell> getCurrentNeighbours(Cell myCell, int k) {
        List<Cell> listOfNeighbours = new ArrayList<>();
        int x = myCell.x;
        int y = myCell.y;
        if (x > 1 && isKLeftNeighbourWay(myCell, k) && (k == 2 || k == 1 && !isKLeftNeighbourVisited(myCell, k))) {
            listOfNeighbours.add(labyrinth.maze[x - 1][y]);
        }
        if (x < labyrinth.height - 2 && isKRightNeighbourWay(myCell, k)
            && (k == 2 || k == 1 && !isKRightNeighbourVisited(myCell, k))) {
            listOfNeighbours.add(labyrinth.maze[x + 1][y]);
        }
        if (y > 1 && isKUnderNeighbourWay(myCell, k) && (k == 2 || k == 1 && !isKUnderNeighbourVisited(myCell, k))) {
            listOfNeighbours.add(labyrinth.maze[x][y - 1]);
        }
        if (y < labyrinth.width - 2 && isKTopNeighbourWay(myCell, k)
            && (k == 2 || k == 1 && !isKTopNeighbourVisited(myCell, k))) {
            listOfNeighbours.add(labyrinth.maze[x][y + 1]);
        }
        return listOfNeighbours;
    }

    private boolean isKLeftNeighbourWay(Cell cell, int k) {
        return labyrinth.maze[cell.x - k][cell.y].type.equals(TypeOfCell.WAY);
    }

    private boolean isKLeftNeighbourVisited(Cell cell, int k) {
        return labyrinth.maze[cell.x - k][cell.y].isVisited;
    }

    private boolean isKRightNeighbourWay(Cell cell, int k) {
        return labyrinth.maze[cell.x + k][cell.y].type.equals(TypeOfCell.WAY);
    }

    private boolean isKRightNeighbourVisited(Cell cell, int k) {
        return labyrinth.maze[cell.x + k][cell.y].isVisited;
    }

    private boolean isKUnderNeighbourWay(Cell cell, int k) {
        return labyrinth.maze[cell.x][cell.y - k].type.equals(TypeOfCell.WAY);
    }

    private boolean isKUnderNeighbourVisited(Cell cell, int k) {
        return labyrinth.maze[cell.x][cell.y - k].isVisited;
    }

    private boolean isKTopNeighbourWay(Cell cell, int k) {
        return labyrinth.maze[cell.x][cell.y + k].type.equals(TypeOfCell.WAY);
    }

    private boolean isKTopNeighbourVisited(Cell cell, int k) {
        return labyrinth.maze[cell.x][cell.y + k].isVisited;
    }

    @Override
    protected List<Cell> compute() {
        return findTheWay(labyrinth.maze, start, end);
    }

    public List<Cell> findTheWay(Cell[][] maze, Cell start, Cell end) {
        List<Cell> way = new ArrayList<>();
        Cell currentCell = maze[start.x][start.y];
        Cell endCell = maze[end.x][end.y];
        cellStack.add(currentCell);
        currentCell.makeVisited();
        while (!cellStack.isEmpty()) {
            if (currentCell.equals(endCell)) {
                way.addFirst(cellStack.pop());
            } else {
                List<Cell> neighbours = getCurrentNeighbours(currentCell, 1);
                for (Cell cell : neighbours) {
                    recursive(neighbours, cell, way);
                }
                if (neighbours.isEmpty()) {
                    cellStack.pop();
                }
                if (!cellStack.isEmpty()) {
                    currentCell = cellStack.peek();
                }
            }
        }
        for (Cell cell : way) {
            cell.setTypeToRoute();
        }
        return way;
    }

    private void recursive(List<Cell> neighbours, Cell currentCell, List<Cell> way) {
        if (neighbours.size() <= 1) {
            cellStack.add(currentCell);
            currentCell.makeVisited();
        } else {
            Solver solver = new Solver(cellStack, labyrinth, currentCell, end);
            solver.fork();
            way.addAll(solver.join());
        }
    }
}
