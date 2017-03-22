package com.company;

import java.util.Vector;

/**
 * Created by lhscompsci on 3/22/17.
 */
public class Snake {
    private int length = 0;
    private char direction;
    private Cell head;
    Vector<Cell> snake;


    public Snake(char direction) {
        this.direction = direction;

        head = new Cell(0,0);
        snake = new Vector<Cell>();
        snake.add(head);
    }

    public void moveHead(char dir) {
        int changeInX = 0;
        int changeInY = 0;
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
        head.move(changeInX, changeInY);
    }
    public void changeDir(char dir) {
        this.direction = dir;
    }

    public void eat() {
        Cell lastOne = 
    //create new cell
    }
    public boolean isAlive() {
        for (int i = 1; i < snake.size(); i ++) {
            if (head.getX() == snake.get(i).getX() && head.getY() == snake.get(i).getY())
                return false;
        }
        return true;
    }
    public int getLength() {
        return this.length;
    }
    public char getDirection() {
        return this.direction;
    }
}
