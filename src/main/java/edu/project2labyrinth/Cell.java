package edu.project2labyrinth;

public class Cell {
    protected final int x;
    protected final int y;

    protected TypeOfCell type;

    protected Boolean isVisited;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = TypeOfCell.WALL;
        this.isVisited = false;
    }

    public void setTypeToWay() {
        this.type = TypeOfCell.WAY;
    }

    public void setTypeToRoute() {
        this.type = TypeOfCell.ROUTE;
    }

    public void makeVisited() {
        this.isVisited = true;
    }

    public void makeUnvisited() {
        this.isVisited = false;
    }
}
