package com.company;

/**
 * Created by lhscompsci on 3/22/17.
 */
public class Cell {
    private int x, y;
    private char dir;
    public Cell(int x, int y, char dir) {
        this.x = y;
        this.y = x;
        this.dir = dir;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void move() {
        int changeInY = 0;
        int changeInX = 0;
        switch(dir) {
            case 'U':
                changeInY = 1;
                break;
            case 'D':
                changeInY = -1;
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
