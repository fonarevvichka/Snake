package com.company;

/**
 * Created by lhscompsci on 3/22/17.
 */
public class Cell {
    private int x, y;
    private char dir;

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                '}';
    }

    public Cell(int x, int y, char dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void move() {
        int changeInX = 0;
        int changeInY = 0;
        switch(dir) {
            case 'U':
                changeInY = -1;
                break;
            case 'D':
                changeInY = 1;
                break;
            case 'L':
                changeInX = -1;
                break;
            case 'R':
                changeInX = 1;
                break;
        }
        this.x += changeInX;
        this.y += changeInY;
    }
    public char getDir() {
        return dir;
    }
    public void setDir(char dir) {
        this.dir = dir;
    }
}
