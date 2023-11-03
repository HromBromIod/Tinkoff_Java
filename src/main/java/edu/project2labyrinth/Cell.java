package edu.project2labyrinth;

public class Cell {
    private final int x;
    private final int y;

    private TypeOfCell type;
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = TypeOfCell.WALL;
    }

    public void setTypeToWay() {
        this.type = TypeOfCell.WAY;
    }
}
