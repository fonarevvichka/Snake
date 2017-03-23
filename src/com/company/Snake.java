package com.company;

import java.util.Vector;

/**
 * Created by lhscompsci on 3/22/17.
 */
public class Snake {
    private int length = 0;
    private char dir;
    private Cell head;
    Vector<Cell> snake;
    Gameboard board = new Gameboard(20, 20);


    public static void main(String[] Args) {
        //------- Clean up ---------//
        char[][] gameboard = new char[22][22];

        for(int x = 0; x < 23; x++) {
            for (int y = 0; y < 23; y++) {
                if (x == 0) {
                    gameboard[x][y] = '|';
                } else if (y == 0) {
                    gameboard[x][y] = '-';
                } else {
                    gameboard[x][y] = ' ';
                }
            }
        }
        //------- Clean up ---------//

        Snake python = new Snake('R');
        while(python.isAlive()) {
            python.updateSnake();
            displayBoard();
        }
    }
    public Snake(char dir) {
        this.dir = dir;

        head = new Cell(0,0, dir);
        snake = new Vector<Cell>();
        snake.add(head);
    }
    public void updateSnake() {
        for(Cell cell : snake) {
            cell.move();
        }
        updateDir();
    }
    public void updateDir() {
        moveHead();
        for(int i = 1; i < snake.size(); i++) {
            snake.get(i).setDir(snake.get(i-1).getDir());
        }
    }
    public void moveHead() {
        head.setDir(dir);
        head.move();
    }
    public void changeDir(char dir) {
        this.dir = dir;
    }
    public void eat() {
        //compare location of food and head
    }
    public void grow() {
        Cell tail = snake.get(snake.size()-1);
        Cell newTail = new Cell(tail.getX(), tail.getY(), tail.getDir());

        snake.add(newTail);
    //create new cell
    }
    public boolean isAlive() {
        if((head.getX() >= 0 && head.getX() <= board.getWidth()) && (head.getY() >= 0 && head.getY() <= board.getHeight())) {
            for (int i = 1; i < snake.size(); i++) {
                if (head.getX() != snake.get(i).getX() && head.getY() != snake.get(i).getY()) {
                    return true;
                }
            }
        }
        return false;
    }
    public int getLength() {
        return this.length;
    }
    public char getDirection() {
        return this.dir;
    }
    public void displayBoard() {
        for (int x = 0; x < 23; x++) {
            for (int y = 0; y < 23; y++) {
                
            }
        }
    }
}
