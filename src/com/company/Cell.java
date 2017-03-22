package com.company;

/**
 * Created by lhscompsci on 3/22/17.
 */
public class Cell {
    private int x, y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void move(int changeInX, int changeInY) {
        this.x += changeInX;
        this.y += changeInY;
    }

}
