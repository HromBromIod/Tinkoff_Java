package edu.hw2.task2;

public class Square extends Rectangle {
    public Square() {
    }

    public Square(int side) {
        super(side, side);
    }

    public void setSide(int side) {
        setHeight(side);
        setWidth(side);
    }
}
