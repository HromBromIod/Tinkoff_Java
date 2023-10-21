package edu.hw2;

public class Task2 {
    private Task2() {
    }

    public static class Rectangle {
        private int width;
        private int height;

        public Rectangle() {
        }

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        double area() {
            return width * height;
        }
    }

    public static class Square extends Task2.Rectangle {
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
}
